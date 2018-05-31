package nst.app.helper;

import nst.app.model.Locale;
import nst.common.Helper;
import nst.dto.LocaleDTO;
import org.springframework.stereotype.Component;

@Component
public class LocaleHelper implements Helper {

    public Locale toModel(LocaleDTO dto) {

        Locale locale = new Locale();
        locale.setId(dto.getId());
        locale.setKey(dto.getKey());
        locale.setLang(dto.getLang());
        locale.setScreen(dto.getScreen());
        locale.setValue(dto.getValue());
        return locale;
    }

    public LocaleDTO fromModel(Locale locale) {

        LocaleDTO dto = new LocaleDTO();
        dto.setId(locale.getId());
        dto.setScreen(locale.getScreen());
        dto.setKey(locale.getKey());
        dto.setLang(locale.getLang());
        dto.setValue(locale.getValue());
        return dto;
    }
}
