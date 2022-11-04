import http from "../http-common";

class UploadFilesService {
  upload(file, onUploadProgress) {
    let formData = new FormData();

    formData.append("file", file);
    console.log("ddd");
    return http.post("/upload", formData, {
     
      headers: {
        "Content-Type": "multipart/form-data",
      },
      
      onUploadProgress,
    });
  }

  getFiles() {
    return http.get("/files");
  }

  deleteFiles(fileName){
    console.log("delete");
    // eslint-disable-next-line no-template-curly-in-string
    return http.delete(`/delete/${fileName}`);
    
  }

}

export default new UploadFilesService();
