import React, { Component } from "react";
import UploadService from "../services/upload-files.service";
import { useState } from "react";

export default class UploadFiles extends Component {
  constructor(props) {
    super(props);
    this.selectFile = this.selectFile.bind(this);
    this.upload = this.upload.bind(this);
    this.deletefiles = this.deletefiles.bind(this);

    this.state = {
      selectedFiles: undefined,
      currentFile: undefined,
      progress: 0,
      message: "",

      fileInfos: [],
    };
  }

  componentDidMount() {
    UploadService.getFiles().then((response) => {
      this.setState({
        fileInfos: response.data,
      });
    });
  }

  selectFile(event) {
    this.setState({
      selectedFiles: event.target.files,
    });
  }

  upload() {
    let currentFile = this.state.selectedFiles[0];

    this.setState({
      progress: 0,
      currentFile: currentFile,
    });

    UploadService.upload(currentFile, (event) => {
      console.log("upload-files");
      this.setState({
        progress: Math.round((100 * event.loaded) / event.total),
      });
    })
      .then((response) => {
        this.setState({
          message: response.data.message,
        });
        return UploadService.getFiles();
      })
      .then((files) => {
        this.setState({
          fileInfos: files.data,
        });
      })
      .catch(() => {
        this.setState({
          progress: 0,
          message: "Could not upload the file!",
          currentFile: undefined,
        });
      });

    this.setState({
      selectedFiles: undefined,
    });
    window.location.reload();
  }

  deletefiles(fileName) {
    //api.get(`/delet/${fileName}`)
    UploadService.deleteFiles(fileName);
  }

  render() {
    const { selectedFiles, currentFile, progress, message, fileInfos } =
      this.state;

    return (
      <div style={{margin: "auto"}}>
        {currentFile && (
          <div className="progress" style={{marginBottom: "10px"}}>
            <div
              className="progress-bar progress-bar-info progress-bar-striped"
              role="progressbar"
              aria-valuenow={progress}
              aria-valuemin="0"
              aria-valuemax="100"
              style={{ width: progress + "%" }}
            >
              {progress}%
            </div>
          </div>
        )}
              
        <label
          className="btn btn-default"
          style={{
            backgroundColor: "#e9e9e9",
            width: "570px",
            height: "100px",
            alignItems: "center",
            justifyContent: "center",
            display: "flex",
            border: "20px",
            borderRadius: "10px",
          }}
        > 
           Click para selecionar arquivo
          
          <input
            type="file"
            onChange={this.selectFile}
            style={{ display: "none" }}

          />
        </label>
        <br />
        <button
          className="btn btn-success"
          disabled={!selectedFiles}
          onClick={this.upload}
          style={{width: "570px", borderRadius: "10px"}}
        >
          Enviar
        </button>

        <div className="alert alert-light" role="alert">
          {message}
        </div>

        <div className="card">
          <div className="card-header">Arquivos enviados</div>
          <ul className="list-group list-group-flush" >
            {fileInfos &&
              fileInfos.map((file, index) => (
                <li className="list-group-item" key={index}>
                  <a href={file.url} style={{textDecoration: "none"}}>{file.name}</a>

                  <button
                    className="btn btn-danger"
                    id="App"
                    style={{float: "right"}}
                    onClick={() => this.deletefiles(file.name)}
                  >
                    Apagar
                  </button>
                </li>
              ))}
          </ul>
        </div>
      </div>
    );
  }
}
