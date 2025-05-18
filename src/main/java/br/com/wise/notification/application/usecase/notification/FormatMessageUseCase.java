package br.com.wise.notification.application.usecase.notification;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FormatMessageUseCase {

    public String replaceMessageValues(String messageTemplate, Map<String, String> values){
        if(StringUtils.isEmpty(messageTemplate) || CollectionUtils.isEmpty(values)){
            return messageTemplate;
        }

        String messageFormatted = messageTemplate;
        for(Map.Entry<String, String> entry : values.entrySet() ){
            final String keyToReplace = String.format("{%s}", entry.getKey());
            final String replacementValue = entry.getValue();
            messageFormatted = messageFormatted.replace(keyToReplace, replacementValue);
        }

        return messageFormatted;
    }
}
