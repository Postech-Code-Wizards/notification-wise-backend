package br.com.wise.notification.application.usecase.notification;

import br.com.wise.notification.infrastructure.controller.exception.BusinessException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class FormatMessageUseCase {

    public String replaceMessageValues(String messageTemplate, Map<String, String> values){
        if(StringUtils.isEmpty(messageTemplate) || CollectionUtils.isEmpty(values)){
            validateFormat(messageTemplate);
            return messageTemplate;
        }

        String messageFormatted = messageTemplate;
        for(Map.Entry<String, String> entry : values.entrySet() ){
            final String keyToReplace = String.format("{%s}", entry.getKey());
            final String replacementValue = entry.getValue();
            messageFormatted = messageFormatted.replace(keyToReplace, replacementValue);
        }

        validateFormat(messageFormatted);
        return messageFormatted;
    }

    private void validateFormat(String message){
        Pattern pattern = Pattern.compile("\\{[^{}]+\\}");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            throw new BusinessException(String.format("The message contains keys with missing values: %s", matcher.group()));
        }
    }
}
