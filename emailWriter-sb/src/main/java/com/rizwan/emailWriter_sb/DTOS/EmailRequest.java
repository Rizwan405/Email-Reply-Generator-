package com.rizwan.emailWriter_sb.DTOS;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EmailRequest {
    private String emailContent;
    private String tone;

}
