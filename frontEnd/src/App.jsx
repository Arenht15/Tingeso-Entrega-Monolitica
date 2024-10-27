import { useState } from 'react'
import Home from './components/Home';
import Forms from './components/Forms';
import './App.css'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import Register from "./components/Register.jsx";
import Simulation from "./components/Simulation.jsx";
import InitialForm from "./components/InitialForm.jsx";

function App() {
  const [count, setCount] = useState(0)

  return (
      <Router>
          <div className="container">
              <Routes>
                  <Route path="/" element={<Home/>} />
                  <Route path="/Forms" element={<Forms/>} /> //Borrrar!
                  <Route path="/InitialForm" element={<InitialForm/>} />
                  <Route path="/Register" element={<Register/>} />
                  <Route path="/Simulation" element={<Simulation/>} />
              </Routes>
          </div>
      </Router>
  );
}

export default App
