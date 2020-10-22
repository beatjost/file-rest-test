package ch.gemdat.fileresttest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class SpringRestTemplate {
  
  private static final String AUTH =""; //TODO: set basic auth
  
  public static void main(String[] args) {
    new SpringRestTemplate().uploadFile();
  }
  
  public void uploadFile() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    headers.setBasicAuth(AUTH);

    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    body.add("file", new FileSystemResource("src/main/resources/file.jpg"));
    
    ResponseEntity<String> response = new RestTemplate().exchange(
        "https://test.portal.ebaugesuche.zh.ch/api/file/v1/ae1a841c-16d0-4b81-98bd-13abbcf89cb5?category=OTHER_DOCUMENTS&source=COMMUNE&sourceDesc=Gemeindemitarbeiter&group=OTHER_DOCUMENTS",
        HttpMethod.PUT, new HttpEntity<>(body, headers), String.class);
    
    System.out.println("-> STATUS: " + response.getStatusCodeValue());
    System.out.println(response.getBody());
  }
  
}