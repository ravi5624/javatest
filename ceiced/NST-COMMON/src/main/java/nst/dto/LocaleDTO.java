package nst.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseDTO;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class LocaleDTO extends BaseDTO {

    private Long id;
    @NotNull
    private String screen;
    @NotNull
    private String lang;
    @NotNull
    private String key;
    @NotNull
    private String value;

    private LocaleDTO(String lang, String value) {
        this.lang = lang;
        this.value = value;
    }

    public static LocaleDTO create(String lang, String value) {
        return new LocaleDTO(lang, value);
    }
}
