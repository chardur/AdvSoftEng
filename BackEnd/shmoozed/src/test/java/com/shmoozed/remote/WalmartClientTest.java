package com.shmoozed.remote;

import com.shmoozed.model.WalmartItem;
import com.shmoozed.service.WalmartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(WalmartClient.class)
public class WalmartClientTest {

  @Autowired
  private WalmartClient fixture;

  @Autowired
  private MockRestServiceServer mockRemoteServer;

  @Test
  public void getItemById() {
    mockRemoteServer.expect(requestTo("/v1/items/12345?format=json&apiKey=<WALMART_API_KEY>"))
      .andRespond(withSuccess(WALMART_ITEM_RESPONSE_JSON, MediaType.APPLICATION_JSON_UTF8));

    WalmartItem results = fixture.getItemById(12345);

    assertThat(results, samePropertyValuesAs(WALMART_ITEM_RESPONSE_OBJECT));
  }

  private static final WalmartItem WALMART_ITEM_RESPONSE_OBJECT =
    new WalmartItem(631796609,
                    0,
                    "Sceptre 50\" Class FHD (1080P) LED TV (X505BV-FSR)",
                    "Electronics/TV & Video/All TVs",
                    "792343250548",
                    349.99,
                    199.99,
                    "https://i5.walmartimages.com/asr/5652268b-ec67-4eb9-8768-0dc7d3a9354a_3.5506e228514ae2a327889ac57910feba.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF",
                    "https://i5.walmartimages.com/asr/5652268b-ec67-4eb9-8768-0dc7d3a9354a_3.5506e228514ae2a327889ac57910feba.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF",
                    "X505BV-FSR",
                    "http://c.affil.walmart.com/t/api01?l=http%3A%2F%2Faffil.walmart.com%2Fcart%2FaddToCart%3Fitems%3D631796609%7C1%26affp1%3D5ng3ElK8UpN4FAdJcQys4Bghacg8YfNH0-Huetnv0oo%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi",
                    null);

  private static final String WALMART_ITEM_RESPONSE_JSON = "{\n" +
    "    \"itemId\": 631796609,\n" +
    "    \"parentItemId\": 631796609,\n" +
    "    \"name\": \"Sceptre 50\\\" Class FHD (1080P) LED TV (X505BV-FSR)\",\n" +
    "    \"msrp\": 349.99,\n" +
    "    \"salePrice\": 199.99,\n" +
    "    \"upc\": \"792343250548\",\n" +
    "    \"categoryPath\": \"Electronics/TV & Video/All TVs\",\n" +
    "    \"shortDescription\": \"With X505BV-FSR, razor-sharp clarity and dazzling color unfolds right before your eyes. Clear QAM tuner is included to make cable connection as easy as possible, without an antenna. From battle scenes to car chases, MEMC 120 will smoothly deliver almost twice as many frames as 60Hz. HDMI input delivers the unbeatable combination of high-definition video and clear audio. A USB port comes in handy when you want to flip through all of your stored pictures and tune into your stored music. More possibilities: with HDMI, VGA, Component and Composite inputs, we offer a convenient balance between the old and new to suit your diverse preferences.\",\n" +
    "    \"longDescription\": \"Sceptre 50&quot; Class FHD (1080P) LED TV (X505BV-FSR):Key Features:Screen Size (Diag.): 49.5 inBacklight Type: LEDResolution: 1080pAspect Ratio: 16:9Effective Refresh Rate: 60HzFrame Rate: MEMC 120Smart Functionality: noDynamic Contrast Ratio: 25,000:1Viewable Angle (H/V): 178 degrees/178 degreesNumber of Colors: 16.7M (8-bit)OSD Language: English, Spanish, FrenchSpeakers/Power Output: 10W x 2&nbsp;Surround Sound ModeConnectivity:Component/Composite Video: 1HDMI: 3VGA: 1Headphone: 1SPDIF Auto Output: 1USB 2.0: 1What's in The Box:Remote ControlWall-mountable:Mount Pattern: 200mm x 200mmScrew Size: M6Screw Length: 10mmSupport and Warranty:1-year limited labor and parts\",\n" +
    "    \"brandName\": \"Sceptre\",\n" +
    "    \"thumbnailImage\": \"https://i5.walmartimages.com/asr/5652268b-ec67-4eb9-8768-0dc7d3a9354a_3.5506e228514ae2a327889ac57910feba.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF\",\n" +
    "    \"mediumImage\": \"https://i5.walmartimages.com/asr/5652268b-ec67-4eb9-8768-0dc7d3a9354a_3.5506e228514ae2a327889ac57910feba.jpeg?odnHeight=180&odnWidth=180&odnBg=FFFFFF\",\n" +
    "    \"largeImage\": \"https://i5.walmartimages.com/asr/5652268b-ec67-4eb9-8768-0dc7d3a9354a_3.5506e228514ae2a327889ac57910feba.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF\",\n" +
    "    \"productTrackingUrl\": \"http://linksynergy.walmart.com/fs-bin/click?id=|LSNID|&offerid=223073.7200&type=14&catid=8&subid=0&hid=7200&tmpid=1082&RD_PARM1=https%253A%252F%252Fwww.walmart.com%252Fip%252FSceptre-50-Class-FHD-1080P-LED-TV-X505BV-FSR%252F631796609%253Faffp1%253D5ng3ElK8UpN4FAdJcQys4Bghacg8YfNH0-Huetnv0oo%2526affilsrc%253Dapi\",\n" +
    "    \"ninetySevenCentShipping\": false,\n" +
    "    \"standardShipRate\": 0,\n" +
    "    \"color\": \"Black\",\n" +
    "    \"shipToStore\": true,\n" +
    "    \"freeShipToStore\": true,\n" +
    "    \"modelNumber\": \"X505BV-FSR\",\n" +
    "    \"productUrl\": \"http://c.affil.walmart.com/t/api01?l=https%3A%2F%2Fwww.walmart.com%2Fip%2FSceptre-50-Class-FHD-1080P-LED-TV-X505BV-FSR%2F631796609%3Faffp1%3D5ng3ElK8UpN4FAdJcQys4Bghacg8YfNH0-Huetnv0oo%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi\",\n" +
    "    \"customerRating\": \"4.468\",\n" +
    "    \"customerRatingImage\": \"http://i2.walmartimages.com/i/CustRating/4_5.gif\",\n" +
    "    \"bestMarketplacePrice\": {\n" +
    "        \"twoThreeDayShippingRate\": 0,\n" +
    "        \"availableOnline\": false,\n" +
    "        \"clearance\": false\n" +
    "    },\n" +
    "    \"categoryNode\": \"3944_1060825_447913\",\n" +
    "    \"rhid\": \"32389\",\n" +
    "    \"bundle\": false,\n" +
    "    \"clearance\": false,\n" +
    "    \"stock\": \"Available\",\n" +
    "    \"attributes\": {\n" +
    "        \"actualColor\": \"Black\",\n" +
    "        \"color\": \"Black\",\n" +
    "        \"ironBankCategory\": \"TVs & Video Displays\"\n" +
    "    },\n" +
    "    \"addToCartUrl\": \"http://c.affil.walmart.com/t/api01?l=http%3A%2F%2Faffil.walmart.com%2Fcart%2FaddToCart%3Fitems%3D631796609%7C1%26affp1%3D5ng3ElK8UpN4FAdJcQys4Bghacg8YfNH0-Huetnv0oo%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi\",\n" +
    "    \"affiliateAddToCartUrl\": \"http://linksynergy.walmart.com/fs-bin/click?id=|LSNID|&offerid=223073.7200&type=14&catid=8&subid=0&hid=7200&tmpid=1082&RD_PARM1=http%253A%252F%252Faffil.walmart.com%252Fcart%252FaddToCart%253Fitems%253D631796609%257C1%2526affp1%253D5ng3ElK8UpN4FAdJcQys4Bghacg8YfNH0-Huetnv0oo%2526affilsrc%253Dapi\",\n" +
    "    \"freeShippingOver35Dollars\": true,\n" +
    "    \"giftOptions\": {},\n" +
    "    \"imageEntities\": [\n" +
    "        {\n" +
    "            \"thumbnailImage\": \"https://i5.walmartimages.com/asr/dfa122ab-3c97-40c7-a2e2-fd7fd8cebcc2_1.59bcfc07fdd03bf00ec53bb5860dc27f.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF\",\n" +
    "            \"mediumImage\": \"https://i5.walmartimages.com/asr/dfa122ab-3c97-40c7-a2e2-fd7fd8cebcc2_1.59bcfc07fdd03bf00ec53bb5860dc27f.jpeg?odnHeight=180&odnWidth=180&odnBg=FFFFFF\",\n" +
    "            \"largeImage\": \"https://i5.walmartimages.com/asr/dfa122ab-3c97-40c7-a2e2-fd7fd8cebcc2_1.59bcfc07fdd03bf00ec53bb5860dc27f.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF\",\n" +
    "            \"entityType\": \"SECONDARY\"\n" +
    "        },\n" +
    "        {\n" +
    "            \"thumbnailImage\": \"https://i5.walmartimages.com/asr/59feddcc-0bbf-44f0-b933-0a057f8fcefb_1.4205962492788928e11282669d46533b.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF\",\n" +
    "            \"mediumImage\": \"https://i5.walmartimages.com/asr/59feddcc-0bbf-44f0-b933-0a057f8fcefb_1.4205962492788928e11282669d46533b.jpeg?odnHeight=180&odnWidth=180&odnBg=FFFFFF\",\n" +
    "            \"largeImage\": \"https://i5.walmartimages.com/asr/59feddcc-0bbf-44f0-b933-0a057f8fcefb_1.4205962492788928e11282669d46533b.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF\",\n" +
    "            \"entityType\": \"SECONDARY\"\n" +
    "        },\n" +
    "        {\n" +
    "            \"thumbnailImage\": \"https://i5.walmartimages.com/asr/d5dccdd0-f695-452b-9a53-ac254dd03225_1.4781ac1f24f00d762233560638347c48.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF\",\n" +
    "            \"mediumImage\": \"https://i5.walmartimages.com/asr/d5dccdd0-f695-452b-9a53-ac254dd03225_1.4781ac1f24f00d762233560638347c48.jpeg?odnHeight=180&odnWidth=180&odnBg=FFFFFF\",\n" +
    "            \"largeImage\": \"https://i5.walmartimages.com/asr/d5dccdd0-f695-452b-9a53-ac254dd03225_1.4781ac1f24f00d762233560638347c48.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF\",\n" +
    "            \"entityType\": \"SECONDARY\"\n" +
    "        },\n" +
    "        {\n" +
    "            \"thumbnailImage\": \"https://i5.walmartimages.com/asr/130de3b5-f865-4b7f-bed5-618294e624d8_1.5fe0c6e4d1360efcce794e14fe6dc1f5.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF\",\n" +
    "            \"mediumImage\": \"https://i5.walmartimages.com/asr/130de3b5-f865-4b7f-bed5-618294e624d8_1.5fe0c6e4d1360efcce794e14fe6dc1f5.jpeg?odnHeight=180&odnWidth=180&odnBg=FFFFFF\",\n" +
    "            \"largeImage\": \"https://i5.walmartimages.com/asr/130de3b5-f865-4b7f-bed5-618294e624d8_1.5fe0c6e4d1360efcce794e14fe6dc1f5.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF\",\n" +
    "            \"entityType\": \"SECONDARY\"\n" +
    "        },\n" +
    "        {\n" +
    "            \"thumbnailImage\": \"https://i5.walmartimages.com/asr/fb6b4df4-ce58-4679-bbaf-c8e49ec0e81e_1.90b9227dc6233e13efb11c39881fc6d2.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF\",\n" +
    "            \"mediumImage\": \"https://i5.walmartimages.com/asr/fb6b4df4-ce58-4679-bbaf-c8e49ec0e81e_1.90b9227dc6233e13efb11c39881fc6d2.jpeg?odnHeight=180&odnWidth=180&odnBg=FFFFFF\",\n" +
    "            \"largeImage\": \"https://i5.walmartimages.com/asr/fb6b4df4-ce58-4679-bbaf-c8e49ec0e81e_1.90b9227dc6233e13efb11c39881fc6d2.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF\",\n" +
    "            \"entityType\": \"SECONDARY\"\n" +
    "        },\n" +
    "        {\n" +
    "            \"thumbnailImage\": \"https://i5.walmartimages.com/asr/c82b3519-2d9b-46b3-bf44-78ed37c10a9c_1.ad3fa162dfc3b6be5352b50ea16c5346.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF\",\n" +
    "            \"mediumImage\": \"https://i5.walmartimages.com/asr/c82b3519-2d9b-46b3-bf44-78ed37c10a9c_1.ad3fa162dfc3b6be5352b50ea16c5346.jpeg?odnHeight=180&odnWidth=180&odnBg=FFFFFF\",\n" +
    "            \"largeImage\": \"https://i5.walmartimages.com/asr/c82b3519-2d9b-46b3-bf44-78ed37c10a9c_1.ad3fa162dfc3b6be5352b50ea16c5346.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF\",\n" +
    "            \"entityType\": \"SECONDARY\"\n" +
    "        },\n" +
    "        {\n" +
    "            \"thumbnailImage\": \"https://i5.walmartimages.com/asr/2f754e02-e096-4708-b166-0587aa723f7a_1.9e9d122985826c23ca343b7b937f583d.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF\",\n" +
    "            \"mediumImage\": \"https://i5.walmartimages.com/asr/2f754e02-e096-4708-b166-0587aa723f7a_1.9e9d122985826c23ca343b7b937f583d.jpeg?odnHeight=180&odnWidth=180&odnBg=FFFFFF\",\n" +
    "            \"largeImage\": \"https://i5.walmartimages.com/asr/2f754e02-e096-4708-b166-0587aa723f7a_1.9e9d122985826c23ca343b7b937f583d.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF\",\n" +
    "            \"entityType\": \"SECONDARY\"\n" +
    "        },\n" +
    "        {\n" +
    "            \"thumbnailImage\": \"https://i5.walmartimages.com/asr/5652268b-ec67-4eb9-8768-0dc7d3a9354a_3.5506e228514ae2a327889ac57910feba.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF\",\n" +
    "            \"mediumImage\": \"https://i5.walmartimages.com/asr/5652268b-ec67-4eb9-8768-0dc7d3a9354a_3.5506e228514ae2a327889ac57910feba.jpeg?odnHeight=180&odnWidth=180&odnBg=FFFFFF\",\n" +
    "            \"largeImage\": \"https://i5.walmartimages.com/asr/5652268b-ec67-4eb9-8768-0dc7d3a9354a_3.5506e228514ae2a327889ac57910feba.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF\",\n" +
    "            \"entityType\": \"PRIMARY\"\n" +
    "        }\n" +
    "    ],\n" +
    "    \"offerType\": \"ONLINE_ONLY\",\n" +
    "    \"isTwoDayShippingEligible\": true,\n" +
    "    \"availableOnline\": true\n" +
    "}";
}
