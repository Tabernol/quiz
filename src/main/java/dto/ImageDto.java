package dto;

import lombok.Data;

@Data
public class ImageDto {
    private Long id;
    private String publicId;
    private String url;
    private Integer width;
    private Integer height;

    public ImageDto(String publicId, String url, Integer width, Integer height) {
        this.publicId = publicId;
        this.url = url;
        this.width = width;
        this.height = height;
    }
}
