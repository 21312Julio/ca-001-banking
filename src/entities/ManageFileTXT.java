package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManageFileTXT {

    private String filename;
    private String path = "c:\\temp\\";

    public ManageFileTXT() {

        this.filename = "customer.txt";
        this.create(path, this.filename);
    }

    public ManageFileTXT(String filename) {
        this.filename = filename;
    }

    public ManageFileTXT(String fName, String lName, String accountNumber, String pin, String accountType) {

        this.filename = fName + lName + "-" + accountNumber + "-" + pin + "-" + accountType;
        this.create(path, filename);
    }

    private void create(String path, String filename) {

        File file = new File(path + filename);
        boolean result;
        try {
            result = file.createNewFile();
            if (result) {
                System.out.println("File created: " + file.getCanonicalPath());
            }
            else {
                System.out.println("File already exists at location: " + file.getCanonicalPath());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writerCustomer(List<String> content) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.path+this.filename, true))) {
            for(String value : content) {
                bw.write(value);
                bw.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> fileReader() {

        List<String> content = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.path+this.filename))) {
            String line = br.readLine();
            while (line != null) {
                content.add(line);
                line = br.readLine();
            }
            return content;
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return content;
    }

    public void deleteFiles() {
        File file = new File(this.path+this.filename);
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

}
