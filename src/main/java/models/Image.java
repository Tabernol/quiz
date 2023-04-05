package models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Data
@NoArgsConstructor
public class Image {
    private Long id;
    private Long questionId;
    private String publicId;
    private String url;
    private Integer width;
    private Integer height;
}
