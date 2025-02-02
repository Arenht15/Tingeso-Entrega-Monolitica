import React, {useState} from 'react';
import {
    AppBar,
    Toolbar,
    Typography,
    Button,
    TextField,
    MenuItem,
    Box,
    DialogTitle,
    DialogContent,
    DialogContentText, DialogActions, Dialog
} from '@mui/material';
import {useNavigate, useParams} from "react-router-dom";
import creditServices from "../services/CreditServices.js";
import savingCapacityServices from "../services/SavingCapacityServices.js";


const Form = () => {
    //Para recibir los datos del formulario anterior
    const { id } = useParams();
    const navigate = useNavigate();
    // Estados para los campos del formulario
    const [Ingress, setIngress] = React.useState(0.0);
    const [statusDicom, setStatusDicom] = React.useState('');
    const [Seniority, setSeniority] = React.useState(-1);
    const [IngressAcum, setIngressAcum] = React.useState(0.0);
    const [amountDebs, setAmountDebs] = React.useState(-1.0);
    const [MontoActual, setMontoActual] = useState(-1.0);
    const [Antiguedad, setAntiguedad] = useState(-1);
    const [MontoAcumulado, setMontoAcumulado] = useState(-1.0);
    const [MontoAhorro, setMontoAhorro] = useState(Array(12).fill(''));
    const [AbonoAhorro, setAbonoAhorro] = useState(Array(12).fill(''));
    const [RetiroAhorro, setRetiroAhorro] = useState(Array(12).fill(''));
    const [TipoEmpleo, setTipoEmpleo] = useState('');
    const [open, setOpen] = useState(false);
    // Estados para los archivos subidos
    const [payFile, setPayFile] = React.useState(null);
    const [dicomFile, setDicomfile] = React.useState(null);
    const [ingressFile, setIngressfile] = React.useState(null);
    const [debs, setDebs] = React.useState(null);
    const [AhorroFile, setAhorroFile] = React.useState(null);


    const handleFileChange = (event, setFile) => {
        const file = event.target.files[0];
        if (file) {
            if (file.type !== 'application/pdf') {
                console.log("Archivo no es PDF");
                event.target.value = null; // Limpiar el campo de archivo
            } else {
                setFile(file);
            }
        }
    };

    const UpdateCredit = async (e) => {
        e.preventDefault();

        if (Ingress === 0.0 || Seniority === -1 || IngressAcum === 0.0 ||
            amountDebs === -1.0 || payFile === null || dicomFile === null ||
            ingressFile === null || debs === null || MontoActual === -1.0 || Antiguedad === -1 || MontoAcumulado === -1.0 || TipoEmpleo === '' ||
            MontoAhorro.some(value => value === '') || AbonoAhorro.some(value => value === '') || RetiroAhorro.some(value => value === '') || AhorroFile === null) {
            console.log("Valor de los campos: ", Ingress, Seniority, IngressAcum, amountDebs, payFile, dicomFile, ingressFile, debs);
            alert("Debe completar todos los campos");
            return;
        }
        const formData = new FormData();
        formData.append("id", id);
        formData.append("Ingress", Ingress);
        formData.append("statusDicom", statusDicom);
        formData.append("seniority", Seniority);
        formData.append("IngressAcum", IngressAcum);
        formData.append("amountDebs", amountDebs);
        formData.append("payFile", payFile);
        formData.append("histDicom", dicomFile);
        formData.append("ingressFile", ingressFile);
        formData.append("debs", debs);

        const formData2 = new FormData();
        formData2.append("MontoActual", MontoActual);
        formData2.append("Antiguedad", Antiguedad);
        formData2.append("MontoAcumulado", MontoAcumulado);
        MontoAhorro.forEach((value) => {
            formData2.append("MontoAhorro", value); // Esto envía múltiples parámetros con el mismo nombre
        });
        AbonoAhorro.forEach((value) => {
            formData2.append("AbonoAhorro", value); // Esto envía múltiples parámetros con el mismo nombre
        });
        RetiroAhorro.forEach((value) => {
            formData2.append("RetiroAhorro", value); // Esto envía múltiples parámetros con el mismo nombre
        });
        formData.append("AhorroFile", AhorroFile);
        formData.append("TipoEmpleo", TipoEmpleo);

        savingCapacityServices.createSavingCapacity(formData2)
            .then((response) => {
                formData.append("idSc", response.data.id)
                creditServices.updateCredit(formData)
                    .then((response2) => {
                        console.log("Credito actualizado: ", response2.data);
                        alert("Solicitud enviada con éxito.");
                        navigate("/");
                    })
                    .catch((e) => {
                        console.error("No se pudo actualizar el credito.", e);
                        alert("No se pudo actualizar el crédito. Inténtalo de nuevo.");
                    });
            }).catch((e) => {
                console.error("No se pudo actualizar el credito.", e);
                alert("No se pudo actualizar el crédito. Inténtalo de nuevo.");
            }
        )
    }


    return (
        <div>
            <AppBar position="fixed" style={{zIndex: 2, backgroundColor: '#17cb17'}}>
                <Toolbar style={{width: '100%', maxWidth: '1200px', display: 'flex', justifyContent: 'space-between'}}>
                    <Typography variant="h6" component="div" sx={{textAlign: 'center'}}>
                        Presta Banco
                    </Typography>
                </Toolbar>
            </AppBar>
            <Box style={{backgroundColor: 'white', color: 'black', border:
                    '1px solid black', padding: '20px', width: '900px', marginTop: '40px'}}>
                <Typography variant="h6" gutterBottom>
                    Datos Laborales
                </Typography>
                <Box sx={{ display: 'flex', gap: '10px', alignItems: 'center' }}>
                    <Typography variant="h8" gutterBottom sx={{ marginRight: '10px' }}>
                        Ingresos Mensuales
                    </Typography>
                    <TextField
                        label="Ingresos Mensuales"
                        value={Ingress > 0 ? Ingress : ""}
                        onChange={e => setIngress(e.target.value.replace(/[^0-9]/g, ''))}
                        variant="outlined"
                        fullWidth
                        margin="normal"
                        placeholder="Ej: 750000"
                        required
                    />
                    <Button
                        variant="contained"
                        component="label"
                        fullWidth
                        margin="dense"
                        required
                        sx={{ textAlign: 'center', width: '40%', backgroundColor: '#17cb17', color: 'white' }}
                    >
                        {ingressFile ? `Archivo subido: ${ingressFile.name}` : "Subir Documento Ingreso"}
                        <input
                            type="file"
                            accept=".pdf"
                            hidden
                            onChange={e => handleFileChange(e, setIngressfile)}
                        />
                    </Button>
                </Box>
                <Box sx={{ display: 'flex', gap: '10px', alignItems: 'center' }}>
                    <Typography variant="h8" gutterBottom sx={{ marginRight: '20px' }}>
                        Historial Crediticio
                    </Typography>

                    <TextField
                        select
                        label="Historial Crediticio"
                        value={statusDicom}
                        onChange={e => setStatusDicom(e.target.value)}
                        variant="outlined"
                        fullWidth
                        margin="normal"
                        required
                    >
                        <MenuItem value={1}>Sin Deudas impagas</MenuItem>
                        <MenuItem value={0}>Con Deudas impagas</MenuItem>
                    </TextField>
                    <Button
                        variant="contained"
                        component="label"
                        fullWidth
                        margin="dense"
                        required
                        sx={{ textAlign: 'center', width: '40%', backgroundColor: '#17cb17', color: 'white' }}
                    >
                        {dicomFile ? `Archivo subido: ${dicomFile.name}` : "Subir Documento Dicom"}
                        <input
                            type="file"
                            accept=".pdf"
                            hidden
                            onChange={e => handleFileChange(e, setDicomfile)}
                        />
                    </Button>

                </Box>
                <Box sx={{ display: 'flex', gap: '10px', alignItems: 'center' }}>
                    <Typography variant="h8" gutterBottom sx={{ marginRight: '10px' }}>
                        Antiguedad y estabilidad
                    </Typography>

                    <TextField
                        select
                        label="Tipo de Trabajador"
                        value={TipoEmpleo}
                        onChange={e => setTipoEmpleo(e.target.value)}
                        variant="outlined"
                        fullWidth
                        margin="normal"
                        required
                        sx={{ width: '60%' }}
                    >
                        <MenuItem value={1}>Independiente</MenuItem>
                        <MenuItem value={0}>Asalariado</MenuItem>
                    </TextField>

                    <TextField
                        label="Años de Antiguedad"
                        value={Seniority >= 0 ? Seniority : ""}
                        onChange={e => setSeniority(e.target.value.replace(/[^0-9]/g, ''))}
                        variant="outlined"
                        fullWidth
                        margin="normal"
                        placeholder="Ej: 3"
                        required
                        sx={{ width: '55%' }}
                    />
                    <TextField
                        label="Ingresos Total en 1 año"
                        value={IngressAcum > 0 ? IngressAcum : ""}
                        onChange={e => setIngressAcum(e.target.value.replace(/[^0-9]/g, ''))}
                        variant="outlined"
                        fullWidth
                        margin="normal"
                        placeholder="Ej: 6000000"
                        required
                        sx={{ width: '60%' }}
                    />
                    <Button
                        variant="contained"
                        component="label"
                        fullWidth
                        margin="dense"
                        required
                        sx={{ textAlign: 'center', width: '60%', backgroundColor: '#17cb17', color: 'white' }}
                    >
                        {payFile ? `Archivo subido: ${payFile.name}` : "Subir Documento Laboral"}
                        <input
                            type="file"
                            accept=".pdf"
                            hidden
                            onChange={e => handleFileChange(e, setPayFile)}
                        />
                    </Button>
                </Box>
                <Box sx={{ display: 'flex', gap: '5px', alignItems: 'center' }}>
                    <Typography variant="h8" gutterBottom sx={{ marginRight: '30px', alignItems: "center"}}>
                        Deudas
                    </Typography>

                    <TextField
                        label="Deuda Total"
                        value={amountDebs >= 0 ? amountDebs : ""}
                        onChange={e => setAmountDebs(e.target.value.replace(/[^0-9]/g, ''))}
                        variant="outlined"
                        fullWidth
                        margin="normal"
                        required
                    />
                    <Button
                        variant="contained"
                        component="label"
                        fullWidth
                        margin="dense"
                        required
                        sx={{ textAlign: 'center', width: '40%', backgroundColor: '#17cb17', color: 'white' }}
                    >
                        {debs ? `Archivo subido: ${debs.name}` : "Subir Documento de las Deudas"}
                        <input
                            type="file"
                            accept=".pdf"
                            hidden
                            onChange={e => handleFileChange(e, setDebs)}
                        />
                    </Button>
                </Box>
            </Box>

            <Box style={{backgroundColor: 'white', color: 'black', border:
                    '1px solid black', padding: '20px', width: '900px', marginTop: '40px'}}>
                <Typography variant="h6" component="div" sx={{textAlign: 'center'}}>
                    Cuenta de ahorro
                </Typography>
                <Box sx={{ display: 'flex', gap: '10px', alignItems: 'center' }}>
                    <Typography variant="h8" gutterBottom sx={{ marginRight: '10px' }}>
                        Datos de la cuenta
                    </Typography>
                    <TextField
                        label="Monto Actual"
                        value={MontoActual >= 0 ? MontoActual : ""}
                        onChange={e => setMontoActual(e.target.value.replace(/[^0-9]/g, ''))}
                        variant="outlined"
                        margin="dense"
                        required
                    />
                    <TextField
                        label="Años de Antiguedad"
                        value={Antiguedad >= 0 ? Antiguedad : ""}
                        onChange={e => setAntiguedad(e.target.value.replace(/[^0-9]/g, ''))}
                        variant="outlined"
                        margin="dense"
                        required
                    />
                    <TextField
                        label="Monto Acumulado"
                        value={MontoAcumulado >= 0 ? MontoAcumulado : ""}
                        onChange={e => setMontoAcumulado(e.target.value.replace(/[^0-9]/g, ''))}
                        variant="outlined"
                        margin="dense"
                        required
                    />
                    <Button
                        variant="contained"
                        component="label"
                        fullWidth
                        margin="dense"
                        required
                        sx={{ textAlign: 'center', width: '40%', backgroundColor: '#17cb17', color: 'white' }}
                    >
                        {AhorroFile ? `Archivo subido: ${AhorroFile.name}` : "Subir Documento de la cuenta"}
                        <input
                            type="file"
                            accept=".pdf"
                            hidden
                            onChange={e => handleFileChange(e, setAhorroFile)}
                        />
                    </Button>

                </Box>
                <Box sx={{ textAlign: 'center', marginTop: "20px"}}>
                    <Typography variant="h8" gutterBottom sx={{ marginRight: '30px', alignItems: "center"}}>
                        Monto Final de los últimos 12 meses
                    </Typography>

                    <Box sx={{ display: 'flex', gap: '2px', flexWrap: 'wrap', alignItems: "center" }}>
                        {Array(12).fill('').map((_, index) => (
                            <TextField
                                key={index}
                                label={`Monto ${index + 1}`}
                                value={MontoAhorro[index] >= 0 ? MontoAhorro[index] : ""}
                                onChange={e => {
                                    const newMontoAhorro = [...MontoAhorro];
                                    newMontoAhorro[index] = e.target.value.replace(/[^0-9]/g, '');
                                    setMontoAhorro(newMontoAhorro);
                                }}
                                variant="outlined"
                                margin="dense"
                                required
                            />
                        ))}
                    </Box>
                    <Typography variant="h8" gutterBottom sx={{ marginRight: '30px', alignItems: "center"}}>
                        Retiros de los últimos 12 meses
                    </Typography>
                    <Box sx={{ display: 'flex', gap: '2px', flexWrap: 'wrap', alignItems: "center" }}>
                        {Array(12).fill('').map((_, index) => (
                            <TextField
                                key={index}
                                label={`Monto ${index + 1}`}
                                value={RetiroAhorro[index] >= 0 ? RetiroAhorro[index] : ""}
                                onChange={e => {
                                    const newRetiroAhorro = [...RetiroAhorro];
                                    newRetiroAhorro[index] = e.target.value.replace(/[^0-9]/g, '');
                                    setRetiroAhorro(newRetiroAhorro);
                                }}
                                variant="outlined"
                                margin="dense"
                                required
                            />
                        ))}
                    </Box>
                    <Typography variant="h8" gutterBottom sx={{ marginRight: '30px', alignItems: "center"}}>
                        Abonos de los últimos 12 meses
                    </Typography>
                    <Box sx={{ display: 'flex', gap: '2px', flexWrap: 'wrap', alignItems: "center" }}>
                        {Array(12).fill('').map((_, index) => (
                            <TextField
                                key={index}
                                label={`Monto ${index + 1}`}
                                value={AbonoAhorro[index] >= 0 ? AbonoAhorro[index] : ""}
                                onChange={e => {
                                    const newAbonoAhorro = [...AbonoAhorro];
                                    newAbonoAhorro[index] = e.target.value.replace(/[^0-9]/g, '');
                                    setAbonoAhorro(newAbonoAhorro);
                                }}
                                variant="outlined"
                                margin="dense"
                                required
                            />
                        ))}
                    </Box>
                </Box>
            </Box>
            <Box sx={{ display: 'flex', gap: '10px', alignItems: 'center', marginTop: '20px' }}>
                <Button
                    variant="contained"
                    sx={{ backgroundColor: '#0b8d0b', color: 'white', flex: 1, margin: '0 10px' }}
                    onClick={() => navigate("/")}
                >
                    Cancelar
                </Button>
            <Button
                variant="contained"
                sx={{ backgroundColor: '#0b8d0b', color: 'white', flex: 1, margin: '0 10px' }}
                onClick={() => setOpen(true)}
            >
                Enviar Solicitud
            </Button>
            </Box>
            <Dialog
                open={open}
                onClose={() => setOpen(false)}
                aria-hidden={!open}
            >
                <DialogTitle>{"Confirmar envio de solicitud"}</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        ¿Estás seguro que quieres enviar la solicitud?
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => setOpen(false)} color="primary">
                        Cancelar
                    </Button>
                    <Button onClick={UpdateCredit} color="primary" autoFocus>
                        Confirmar
                    </Button>
                </DialogActions>
            </Dialog>

            <style>{`
                      .full-height {
                        min-height: 100vh;
                        display: flex;
                        flex-direction: column; /* Cambiar a columna para que el AppBar esté arriba */
                        align-items: center;
                        justify-content: center;
                        position: relative;
                      }
        
                      body {
                          margin: 0;
                          background-image: radial-gradient(circle, rgba(173, 255, 47, 0.3) 10%, rgba(255, 255, 255, 0) 15%);
                          background-size: 20px 20px;
                          background-color: white;
                          overflow: auto;
                      }
            `}</style>
        </div>
    )
}
export default Form