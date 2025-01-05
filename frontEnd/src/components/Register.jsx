import React from 'react';
import {useState} from 'react';
import userServices from '../services/UserService';
import {AppBar, Toolbar, Container, Typography, Button, TextField, MenuItem, Box} from '@mui/material';
import {useNavigate} from "react-router-dom";
const Register = () => {
        const navigate = useNavigate();
        //Constantes de los campos
        const [rut, setRut] = useState("");
        const [email, setEmail] = useState("");
        const [name, setName] = useState("");
        const [surname, setSurname] = useState("");
        const [birthdate, setBirthDate] = useState({ year: "", month: "", day: "" });
        const [identification, setIdentification] = useState(null);

    const handleFileChange = (event) => {
        const file = event.target.files[0];
        if (file) {
            if (file.type !== 'application/pdf') {
                alert('Por favor, suba un archivo PDF.');
                event.target.value = null; // Limpiar el campo de archivo
            } else {
                console.log("Archivo subido: ", file);
                setIdentification(file);
            }
        }
    };

    const validateBirthdate = (birthdate) => {
        const { year, month, day } = birthdate;
        const currentYear = new Date().getFullYear();
        const maxDaysInMonth = new Date(year, month, 0).getDate();
        if (year < 1950 || year > currentYear || month < 1 || month > 12 || day < 1 || day > maxDaysInMonth) {
            return true;
        }
        return false;
    };

    const validateRUT = (rut) => {
        const regex = /^[0-9]{7,8}-[0-9kK]{1}$/;
        if (!regex.test(rut)) {
            console.log("Rut incorrecto");
            return true;
        }
        return false;
    };

    const validateEmail = (email) => {
        //Se verifica que el email tenga un formato correcto
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!regex.test(email)) {
            return true;
        } else {
            return false;
        }
    };

    const saveUser = async (e) => {
        e.preventDefault();
        await new Promise(resolve => setTimeout(resolve, 1));

        if (!rut || !email || !name || !surname || !birthdate || !identification) {
            alert('Todos los campos son obligatorios.');
            return;
        }
        if (validateRUT(rut)){
            alert('El formato del RUT es incorrecto. Debe ser del tipo 12345678-9.');
            return;
        }
        if (validateBirthdate(birthdate)){
            alert("Algun valor de la fecha de nacimiento esta fuera de rango");
            return;
        }
        if (validateEmail(email)) {
            alert('El formato del correo electrónico es incorrecto.');
            return;
        }

        const formattedBirthdate = `${birthdate.year}-${String(birthdate.month).padStart(2, '0')}-${String(birthdate.day).padStart(2, '0')}`;
        const formData = new FormData();
        formData.append("rut", rut);
        formData.append("email", email);
        formData.append("name", name);
        formData.append("surname", surname);
        formData.append("birthdate", formattedBirthdate);
        formData.append("identification", identification);

        userServices.save(formData)
            .then((response) => {
                console.log("Usuario ha sido añadido.", response.data);
                alert('Usuario registrado con éxito.');
                navigate("/");
            })
            .catch((error) => {
                if (error.response) {
                    // La solicitud se hizo y el servidor respondió con un código de estado
                    console.error("Error del servidor:", error.response.data);
                    console.log("Error 1");
                } else if (error.request) {
                    // La solicitud se hizo pero no se recibió respuesta
                    console.error("No se recibió respuesta:", error.request);
                } else {
                    // Algo sucedió al configurar la solicitud
                    console.error("Error al registrar el usuario:", error.message);
                }
                alert('El rut ingresado ya existe.');
            });
    };


        return (
                <div className="full-height">
                    <AppBar position="fixed" style={{zIndex: 2, backgroundColor: '#17cb17'}}>
                        <Toolbar style={{width: '100%', maxWidth: '1200px', display: 'flex', justifyContent: 'space-between'}}>
                            <Typography variant="h6" component="div" sx={{textAlign: 'center'}}>
                                Presta Banco
                            </Typography>
                        </Toolbar>
                    </AppBar>
                    <Box sx={{ backgroundColor: 'white', color: 'black', border: '1px solid black',
                        padding: '20px', width: '600px' }}>
                        <Typography variant="h6" gutterBottom>
                            Formulario de Registro
                        </Typography>
                        <Box sx={{ display: 'flex', gap: '10px'}}>
                                <TextField
                                    label="RUT"
                                    value={rut}
                                    onChange={e => setRut(e.target.value)} // Solo actualiza el estado
                                    variant="outlined"
                                    fullWidth
                                    margin="normal"
                                    placeholder="Ej: 12345678-9"
                                    required
                                />

                                <TextField
                                    label="Correo Electrónico"
                                    value={email}
                                    onChange={e => {
                                        setEmail(e.target.value);
                                        validateEmail(e.target.value);
                                    }}
                                    variant="outlined"
                                    fullWidth
                                    margin="normal"
                                    placeholder="Ej: ejemplo@correo.com"
                                    type="email"
                                    required
                                />
                        </Box>
                        <Box sx={{ display: 'flex', gap: '10px' }}>
                            <TextField label="Nombre"
                                       value = {name}
                                       onChange={(e) => setName(e.target.value.replace(/[^a-zA-Z\s]/g, ''))}
                                       variant="outlined" fullWidth margin="normal"
                                       placeholder="Ej: Juan" required />
                            <TextField label="Apellido" variant="outlined" fullWidth margin="normal"
                                        value = {surname}
                                        onChange={(e)=>setSurname(e.target.value.replace(/[^a-zA-Z\s]/g, ''))}
                                       placeholder="Ej: Pérez" required />
                        </Box>
                        <Box sx={{ display: 'flex', justifyContent: 'center', gap: '20px', alignItems: 'center' }}>
                            <TextField
                                label="Año de Nacimiento"
                                value={birthdate.year || ""}
                                onChange={e => setBirthDate({ ...birthdate, year: e.target.value.replace(/[^0-9]/g, '') })}
                                variant="outlined"
                                fullWidth
                                margin="dense"
                                placeholder="Ej: 25"
                                required
                                style={{ width: '30%' }}
                            />
                            <TextField
                                label="Mes de Nacimiento"
                                value={birthdate.month || ""}
                                onChange={e => setBirthDate({ ...birthdate, month: e.target.value.replace(/[^0-9]/g, '') })}
                                variant="outlined"
                                fullWidth
                                margin="dense"
                                placeholder="Ej: 9"
                                required
                                style={{ width: '30%' }}
                            />
                            <TextField
                                label="Día de Nacimiento"
                                value={birthdate.day || ""}
                                onChange={e => setBirthDate({ ...birthdate, day: e.target.value.replace(/[^0-9]/g, '') })}
                                variant="outlined"
                                fullWidth
                                margin="dense"
                                placeholder="Ej: 25"
                                required
                                style={{ width: '30%' }}
                            />
                        </Box>

                        <Box sx={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
                            <TextField
                                label="Subir Archivo"
                                type="file"
                                onChange={handleFileChange}
                                fullWidth
                                margin="normal"
                            />
                        </Box>

                        <Button variant="contained" color="primary"
                                style={{ backgroundColor: '#01B701' }}
                                    fullWidth
                                onClick={(e) => saveUser(e)}>
                            Registrarse
                        </Button>
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
                          overflow: auto;
                      }
                    `}</style>
                </div>
        );
}
export default Register;