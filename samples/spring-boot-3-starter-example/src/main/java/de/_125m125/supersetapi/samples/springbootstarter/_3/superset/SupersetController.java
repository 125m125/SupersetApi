package de._125m125.supersetapi.samples.springbootstarter._3.superset;

import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import de._125m125.supersetapi.adapter.SupersetAdapter;
import de._125m125.supersetapi.model.SupersetFactory;
import de._125m125.supersetapi.model.security.GuestTokenRequestData;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/superset/")
@AllArgsConstructor
@DependsOn("supersetAdapter")
public class SupersetController {
  private final SupersetClientProperties properties;
  private final SupersetAdapter supersetAdapter;
  private final SupersetFactory supersetFactory;

  @GetMapping("/details")
  public SupersetEmbedDetailsDto getSupersetEmbedDetails() {
    return new SupersetEmbedDetailsDto(properties.getId(), properties.getRootUrl());
  }

  @PostMapping("/guestToken")
  public String createGuestToken(@RequestParam String firstName, @RequestParam String lastName,
      @RequestParam String guestName) {
    GuestTokenRequestData requestData =
        supersetFactory.createGuestTokenRequestDataBuilder(firstName, lastName, guestName)
            .addResource(properties.getId(), properties.getType())
            .addRlsRule(properties.getRlsRule().orElse("TRUE")).build();
    return supersetAdapter.createGuestToken(requestData).getToken();
  }
}
