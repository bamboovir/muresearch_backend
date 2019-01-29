package com.bamboovir.muresearchboost.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ContentFile {
    @Id
    private Long id;
    private String fileType;
    private String fileSize;
    private String fileUrl;

    public ContentFile mock(){
        return new ContentFile()
                .setId(666L).
                setFileSize("10K")
                .setFileType("PNG")
                .setFileUrl("http://google.com");
    }
}
