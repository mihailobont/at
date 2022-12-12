package com.example.AT;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ReaderService {

    private Integer maxKcal = 0;
    private Integer currentElfKcal = 0;

    /* This can be overloaded for different types of arguments, e.g:  File, String (File path), MultipartFile etc. */
    public Integer findMaxKcal(String filePath){

        this.maxKcal = 0;

        try {
            Stream<String> stream = Files.lines(Paths.get(filePath));

            stream.forEach(this::checkLine);

            stream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return this.maxKcal;
    }

    private void checkLine(String line){

        if(line.length() == 0){
            if(this.maxKcal < this.currentElfKcal){
                this.maxKcal = this.currentElfKcal;
            }
            this.currentElfKcal = 0;
        }else{
            this.currentElfKcal += Integer.valueOf(line);
        }
    }
}
