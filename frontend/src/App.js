import React from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";

import UploadFiles from "./components/upload-files.component";

function App() {
  return (
    <div className="container" style={{ width: "600px", margin:"auto" }}>
      <div style={{ margin: "20px"}}>
        <h3>NFS UPLOAD</h3>

        <br/>
        <span>Tamanho suportado: 2,5 Mb</span>
      </div>
      <UploadFiles />
    </div>
  );
}



export default App;
