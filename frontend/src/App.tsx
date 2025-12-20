import { useState, useEffect } from 'react'
import './App.css'

function App() {
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetch(import.meta.env.VITE_API_URL + "/api/health")
      .then(res => res.text())
      .then(setMessage)
      .catch(console.error);
  }, []);

  return (
    <div>
      <h1>Chess Review App</h1>
      <p>Backend says: {message}</p>
    </div>
  )
}

export default App
