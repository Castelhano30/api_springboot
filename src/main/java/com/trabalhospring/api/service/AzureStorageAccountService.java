package com.trabalhospring.api.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

@Service
public class AzureStorageAccountService {

    public String uploadFileToAzure(MultipartFile file) throws IOException {
        String connectionString = "DefaultEndpointsProtocol=https;AccountName=amarzenamentoimg;AccountKey=+ZsuMVQ1G4s8PYRfAKtqf+wFskeSyIO6K5d6HopdIW1PkpGMyGrYsLwhZMdJ9yQK1dLOeNor7O+W+AStt5YEVQ==;EndpointSuffix=core.windows.net";

        BlobContainerClient client = new BlobContainerClientBuilder()
                .connectionString(connectionString)
                .containerName("imagem")
                .buildClient();

        BlobClient blob = client.getBlobClient(file.getOriginalFilename());

        blob.upload(file.getInputStream(), file.getSize(), true);

        return "https://amarzenamentoimg.blob.core.windows.net/imagem/" + file.getOriginalFilename();
    }
}
