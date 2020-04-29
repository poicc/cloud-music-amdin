package com.soft1851.music.admin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CRQ
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {
    private int currentSize;
    private int size;
}
