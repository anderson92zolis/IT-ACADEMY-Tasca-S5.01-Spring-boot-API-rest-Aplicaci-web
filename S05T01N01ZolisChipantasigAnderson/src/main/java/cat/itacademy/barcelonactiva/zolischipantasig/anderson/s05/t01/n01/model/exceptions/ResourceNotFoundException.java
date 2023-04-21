package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// where I found the exception:  https://github.com/RameshMF/springboot-blog-rest-api/blob/main/src/main/java/com/springboot/blog/exception/ResourceNotFoundException.java

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{


    private String resourceName;
    private String fieldName;
    private int id;

    public ResourceNotFoundException(String resourceName, String fieldName, int id) {
        super(String.format("%s, not found with %s : '%s'", resourceName, fieldName, id)); // Post not found with id : 1
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public long getId() {
        return id;
    }
}