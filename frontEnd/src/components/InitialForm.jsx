import React from 'react';
import {AppBar, Toolbar, Typography, Button, TextField, MenuItem, Box} from '@mui/material';
const InitialForm = () => {
    return(
        <div className="full-height">
            <AppBar position="fixed" style={{zIndex: 2, backgroundColor: '#17cb17'}}>
                <Toolbar style={{width: '100%', maxWidth: '1200px', display: 'flex', justifyContent: 'space-between'}}>
                    <Typography variant="h6" component="div" sx={{textAlign: 'center'}}>
                        Presta Banco
                    </Typography>
                </Toolbar>
            </AppBar>
            <Box>
                <Typography variant="h4" component="div" sx={{textAlign: 'center', marginTop: '100px'}}>
                    Formulario de Solicitud de Crédito
                </Typography>

            </Box style={{backgroundColor: 'white', color: 'black', border:
            '1px solid black', padding: '20px', width: '700px', marginTop: '20px' }}>
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
export default InitialForm