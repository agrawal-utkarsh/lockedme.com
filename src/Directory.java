import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Directory {

    private List<File> files;
    private String path;

    public Directory() {
    }

    public Directory(String path) {
        this.path = path;
        initialize();
    }

    private static final Comparator<File> sortFileByName() {
        Comparator comp = new Comparator<File>() {
            @Override
            public int compare(File file1, File file2) {
                return file1.getName().compareTo(file2.getName());
            }
        };
        return comp;
    }

    private void initialize() {
        this.files = new ArrayList<>();
        File[] files = new File(this.path).listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null.
        for (File file : files) {
            this.files.add(file);
        }

    }

    public List<File> getFiles() {
        return files;
    }

    public Directory setFiles(List<File> files) {
        this.files = files;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Directory setPath(String path) {
        this.path = path;
        return this;
    }

    public void listFilesInSortedOrder() {
        System.out.println();
        Collections.sort(this.files, Directory.sortFileByName());
        for (File file : this.files) {
            System.out.println(file.getName() + "\t");
        }
        System.out.println();
        //        return List.of(this.path);
    }

    public String addFile(String fileName) {
        try {
            Path filePath = Files.createFile(Path.of(this.path + "/" + fileName));
            initialize();
            if (filePath.toFile().exists()) {
                return "File Created Successfully.";
            } else {
                return "Creation Operation Failed.";
            }
        } catch (Exception e) {
            return "File Already Exists.";
        }
    }

    public String deleteFile(String fileName) {
        try {
            boolean result = Files.deleteIfExists(Path.of(this.path + "/" + fileName));
            initialize();
            if (result) {
                return "File Deleted Successfully.";
            } else {
                return "Delete Operation Failed.";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String searchFile(String fileName) {
        Path filePath = Paths.get(this.path + "/" + fileName);
        if (filePath.toFile().exists()) {
            return "File Exists.";
        } else {
            return "File Doesn't Exist.";
        }

    }

    public String executeOperation(Integer operation, String fileName) {
        String operationResponse = "";
        switch (operation) {
            case 1:
                operationResponse = this.addFile(fileName);
                break;
            case 2:
                operationResponse = this.deleteFile(fileName);
                break;
            case 3:
                operationResponse = this.searchFile(fileName);
                break;
        }
        return operationResponse;
    }

}
