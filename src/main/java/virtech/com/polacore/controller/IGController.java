package virtech.com.polacore.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import virtech.com.polacore.model.dto.IGResponse;
import virtech.com.polacore.utils.Util;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
@Slf4j
public class IGController {

    @Autowired
    Logic logic;

    @GetMapping("/")
    public ModelAndView loginPageRequest() {
        return new ModelAndView("login");
    }

    @GetMapping("/redirect")
    public RedirectView redirectToInstagram()  {
        try {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl(logic.buildRedirectUrl());
            return redirectView;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/callbackurl")
    public void callback(@RequestParam("code") String code) throws IOException {

        URL url = new URL(logic.accessTokenUrl());
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);

        OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
        writer.write(logic.buildAccessTokenQueryUrl(code));
        writer.flush();
        String jsonReponse = logic.getJsonResponse(urlConnection.getInputStream());
        log.debug(jsonReponse);
        IGResponse response = (IGResponse) Util.convertStringJsonToObject(logic.getJsonResponse(urlConnection.getInputStream()), IGResponse.class);
        log.debug("prueba. {}", response);

    }
}
