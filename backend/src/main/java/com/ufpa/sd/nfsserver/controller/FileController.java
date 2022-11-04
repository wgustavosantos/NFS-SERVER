package com.ufpa.sd.nfsserver.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.ufpa.sd.nfsserver.model.FileInfo;
import com.ufpa.sd.nfsserver.service.storage.FileService;

@RestController
@RequestMapping("api/nfs-server") /* Definindo o endpoint do webservice */
@CrossOrigin("http://localhost:8081")
public class FileController {
	
	@Autowired
	private FileService disk;
	
	@PostMapping("/upload")
	public void upload(@RequestParam MultipartFile file) {
		
		disk.salvar(file);	
	}
	
	@GetMapping("/files")
	public ResponseEntity<List<FileInfo>> getListFiles(){
		
		List<FileInfo> fileInfos = disk.loadAll().map(path -> {
		      String filename = path.getFileName().toString();
		      String url = MvcUriComponentsBuilder
		          .fromMethodName(FileController.class, "getFile", path.getFileName().toString()).build().toString();
		      
		      return new FileInfo(filename, url);
		    }).collect(Collectors.toList());
		return  ResponseEntity.status(HttpStatus.OK).body(fileInfos);
		
	}
	
	@GetMapping("/files/{filename:.+}")
	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
	    Resource file = disk.load(filename);
		    return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
	
	@DeleteMapping("/delete/{fileName}")
	public ResponseEntity<Void> delete(@PathVariable String fileName){
		
		disk.delete(fileName);
		
		return ResponseEntity.noContent().build();
		
	}
}
