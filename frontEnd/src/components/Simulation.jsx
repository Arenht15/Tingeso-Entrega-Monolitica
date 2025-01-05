import React from 'react';
import {useState} from 'react';
import userServices from '../services/UserService';
import {AppBar, Toolbar, Typography, Button, TextField, MenuItem, Box, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper} from '@mui/material';
import {useNavigate} from "react-router-dom";

const Simulation = () => {
    const navigate = useNavigate();
    const [rut, setRut] = useState("");
    const [ErrorRut, setErrorRut] = useState(false);
    const [type, setType] = useState(1);
    const [amount, setAmount] = useState(0.0);
    const [term, setYears] = useState(0);
    const [rate, setRate] = useState(0.0);
    const [simulatedAmount, setSimulatedAmount] = useState(null);
    const [showSimulatedAmount, setShowSimulatedAmount] = useState(false);

    const existeRut = async (rut) => {
        try {
            const response = await userServices.validUser(rut);
            setErrorRut(response.data);
            return response.data;
        } catch (e) {
            console.log("Hubo un error al encontrar el monto", e);
            return false;
        }
    }

    const getSimulatedAmount = async (type, amount, term, rate) => {
        const isValidRut = await existeRut(rut);
        if (Object.keys(isValidRut).length === 0) {
            alert("Debe Registrarse para poder simular un credito");
            return;
        }
        userServices.simulateCredit(type, amount, term, rate)
            .then((response) => {
                console.log("Monto simulado: ", response.data);
                if(response.data === 0) {
                    if(type == 1){
                        alert("El interes debe estar entre el rango 3.5% - 5.0%, para una primera vivienda");
                        return;
                    }
                    if(type == 2){
                        alert("El interes debe estar entre el rango 4.0% - 6.0%, para una segunda vivienda");
                        return;
                    }
                    if(type == 3){
                        alert("El interes debe estar entre el rango 5.0% - 6.0%, para propiedades comerciales");
                        return;
                    }
                    if(type == 4){
                        alert("El interes debe estar entre el rango 4.5% - 6.0%, para una remodelacion");
                        return;
                    }
                    alert("No se pudo calcular el monto del credito");
                }
                if (type == 1 && term > 30) {
                    alert("El plazo maximo para una primera vivienda es de 30 años");
                    return;
                }
                if (type == 2 && term > 20) {
                    alert("El plazo maximo para una segunda vivienda es de 20 años");
                    return;
                }
                if (type == 3 && term > 25) {
                    alert("El plazo maximo para propiedades comerciales es de 25 años");
                    return;
                }
                if (type == 4 && term > 15) {
                    alert("El plazo maximo para una remodelacion es de 15 años");
                    return;
                }
                setSimulatedAmount(response.data);
                setShowSimulatedAmount(true);
            })
            .catch((e) => {
                alert("Hubo un error al calcular el credito", e);
            });
    }

    const LoanTable = () => {
        const rows = [
            {
                type: "Primera Vivienda",
                maxTerm: "30 años",
                interestRate: "3.5% - 5.0%",
                maxFinancing: "80% del valor de la propiedad",
            },
            {
                type: "Segunda Vivienda",
                maxTerm: "20 años",
                interestRate: "4.0% - 6.0%",
                maxFinancing: "70% del valor de la propiedad",
            },
            {
                type: "Propiedades Comerciales",
                maxTerm: "25 años",
                interestRate: "5.0% - 7.0%",
                maxFinancing: "60% del valor de la propiedad",
            },
            {
                type: "Remodelación",
                maxTerm: "15 años",
                interestRate: "4.5% - 6.0%",
                maxFinancing: "50% del valor actual de la propiedad",
            },
        ];

        return (
            <TableContainer component={Paper} style={{ maxWidth: '400px' }}>
                <Table>
                    <TableHead style={{ backgroundColor: '#01B701' }}>
                        <TableRow>
                            <TableCell style={{ color: 'white', width: '200px' }}><strong>Tipo de Préstamo</strong></TableCell>
                            <TableCell style={{ color: 'white', width: '150px' }}><strong>Plazo Máximo</strong></TableCell>
                            <TableCell style={{ color: 'white', width: '150px' }}><strong>Tasa Interés (Anual)</strong></TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {rows.map((row, index) => (
                            <TableRow key={index}>
                                <TableCell>{row.type}</TableCell>
                                <TableCell>{row.maxTerm}</TableCell>
                                <TableCell>{row.interestRate}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        );
    };


    return(
        <div className="full-height">
            <AppBar position="fixed" style={{zIndex: 2, backgroundColor: '#17cb17'}}>
                <Toolbar style={{width: '100%', maxWidth: '1200px', display: 'flex', justifyContent: 'space-between'}}>
                    <Typography variant="h6" component="div" sx={{textAlign: 'center'}}>
                        Presta Banco
                    </Typography>
                </Toolbar>
            </AppBar>
            <Box style={{ display: 'flex', gap: '20px', alignItems: 'center' }}>
                <LoanTable />
                <Box style={{backgroundColor: 'white', color: 'black', border:
                    '1px solid black', padding: '20px', width: '700px', marginTop: '20px' }}>
                    <Typography variant="h6" gutterBottom>
                        Simular Crédito
                    </Typography>
                    <Box sx={{ display: 'flex', gap: '10px' }}>
                        <TextField
                            label="Rut"
                            value={rut || ""}
                            onChange={e => setRut(e.target.value)}
                            variant="outlined"
                            fullWidth
                            margin="normal"
                            placeholder="Ej: 12345678-9"
                            required
                        />
                        <TextField
                            select
                            label="Tipo de Crédito"
                            value={type}
                            onChange={e => setType(e.target.value)}
                            variant="outlined"
                            fullWidth
                            margin="normal"
                            required
                        >
                            <MenuItem value={1}>Primera Vivienda</MenuItem>
                            <MenuItem value={2}>Segunda Vivienda</MenuItem>
                            <MenuItem value={3}>Propiedades Comerciales</MenuItem>
                            <MenuItem value={4}>Remodelación</MenuItem>
                        </TextField>
                    </Box>

                    <Box sx={{ display: 'flex', gap: '10px' }}>
                        <TextField
                            label="Tasa De Interés"
                            value={rate || ""}
                            onChange={e => setRate(e.target.value.replace(/[^0-9.]/g, ''))}
                            variant="outlined"
                            fullWidth
                            margin="normal"
                            placeholder="Ej: 3.9"
                            required
                        />
                        <TextField
                            label="Plazo Máximo De Pago (En Años)"
                            value={term || ""}
                            onChange={e => setYears(e.target.value.replace(/[^0-9]/g, ''))}
                            variant="outlined"
                            fullWidth
                            margin="normal"
                            placeholder="Ej: 12"
                            required
                        />
                    </Box>
                    <TextField
                        label="Monto"
                        value={amount || ""}
                        onChange={e => setAmount(e.target.value.replace(/[^0-9]/g, ''))}
                        variant="outlined"
                        fullWidth
                        margin="normal"
                        placeholder="Ej: 1000000"
                        required
                    />
                    <Button variant="contained" color="primary"
                            style={{ backgroundColor: '#01B701' }}
                            fullWidth
                            onClick={(e) => getSimulatedAmount(type, amount, term, rate)}>
                        Calcular
                    </Button>
                    {showSimulatedAmount && (
                        <Typography variant="h6" style={{ marginTop: '20px', color: 'green' }}>
                            {`Cuota Mensual: $${Math.trunc(simulatedAmount).toLocaleString('de-DE')}`}
                        </Typography>
                    )}

                    <Button
                        style={{ marginTop: '20px', backgroundColor: '#0b8d0b', color: 'white' }}
                        onClick={() => navigate('/')}
                    >
                        Volver al Menu principal
                    </Button>
                </Box>
            </Box>

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
                  overflow: hidden;
              }
            `}</style>

        </div>
    )
}

export default Simulation