package com.ufpa.sd.nfsserver.service.storage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ufpa.sd.nfsserver.model.FileInfo;

@Service
public class FileService {

	@Value("${nfs-server.storage.raiz}")
	private String raiz;

	@Value("${nfs-server.storage.diretorio}")
	private String diretorioFiles;

	@Value("${nfs-server.storage.mount}")
	private String mount;

	public void salvar(MultipartFile file) {

		this.write(file);

	}

	public void save(MultipartFile file, Path path) {

		FileInfo fileInfo = new FileInfo();
		fileInfo.setName(file.getOriginalFilename());
		fileInfo.setUrl(path.toAbsolutePath().toString());

	}

	public void delete(String fileName) {

		String pathNfsServer = mount + raiz;
		Path diretorioPath = Paths.get(pathNfsServer, diretorioFiles);
		Path arquivoPath = diretorioPath.resolve(fileName);
		try {
			FileSystemUtils.deleteRecursively(arquivoPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void write(MultipartFile arquivo) {

		String pathNfsServer = mount + raiz;

		Path diretorioPath = Paths.get(pathNfsServer, diretorioFiles);
		Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());
		// Path absolutePath = new Path()
		try {
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toAbsolutePath());
		} catch (IOException e) {
			throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
		}

		// delete(arquivoPath);
	}

	public Stream<Path> loadAll() {

		String pathNfsServer = mount + raiz;
		Path diretorioPath = Paths.get(pathNfsServer, diretorioFiles);

		try {
			return Files.walk(diretorioPath, 1).filter(path -> !path.equals(diretorioPath))
					.map(diretorioPath::relativize);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Não foi possível carregar os arquivos!");
		}

	}

	public Resource load(String filename) {

		String pathNfsServer = mount + raiz;
		Path diretorioPath = Paths.get(pathNfsServer, diretorioFiles);

		try {
			Path file = diretorioPath.resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

}
