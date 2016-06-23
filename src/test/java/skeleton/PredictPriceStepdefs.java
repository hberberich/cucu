package skeleton;

import Helper.WDUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PredictPriceStepdefs {

    @When("^Test data light \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void testDataLight(String natCode, String predict, String articleId, String makemodelId, String makemodel, String created, String pricePublicEuro, String bodyTypeId, String fuelId, String gearingId, String mileage, String power, String customer, String seasonMonth, String fregYear, String testdata) throws Throwable {
        //https://www.autoscout24.de/fahrzeugbewertung/ergebnis?ep.emissionClassId=0&ep.eid[32]=32&ep.eid[15]=15&makeId=9&ep.eid[18]=18&ep.consumption=9.2&mileage=170000&natCode=10117856&ep.eid[1]=1&ep.doors=3
        // &equipmentLine=1.8+T+quattro&vehicleGroupId=15637_6_3&fuelId=B&ep.eid[121]=121&ep.eid[2]=2&ep.eid[39]=39&ep.eid[117]=117&ep.eid[17]=17&ep.eid[41]=41&featurebee=price-range-with-medium=true|TATSU-997-mixed-models=true
        // &ep.eid[13]=13&ep.eid[3]=3&ep.eid[16]=16&ep.eid[26]=26&firstRegistrationMonth=06&power=165&ep.eid[12]=12&ep.eid[42]=42&action=calc&ep.eid[116]=116&ep.eid[19]=19&firstRegistrationYear=2002&modelSize=light

        String baseUrl = WDUtil.getWebDriver().getCurrentUrl() + "/ergebnis?";
        String makeId; String month; String year; String model; String fuel; String powerForm; String equipmentline; String mileageForm;

        baseUrl += "makeId=" + makemodelId.substring(0, makemodelId.indexOf("_"));
        baseUrl += "&mileage=" + mileage;
        baseUrl += "&natCode=" + natCode; //"10111370" ;// Audi 1 2ter"10117856"; 2002// Check
        //baseUrl += "&natCode=" + "65529" ;// Peugeot Belgien
        //baseUrl += "&ep.doors=" + "3";

        baseUrl += "&equipmentLine=" + "xxxSportback"; //"xxxx1.8+T+quattro"; // Check
        //baseUrl += "&equipmentLine=" + "1.4i+Trendy"; // Check
        baseUrl += "&vehicleGroupId=" + makemodelId.substring(makemodelId.indexOf("_")+1) + "_" + bodyTypeId + "_5"; // ToDo: Check doors!
        baseUrl += "&fuelId=" + fuelId; //((fuelId.equals("B")) ? "ep.eid[121]=121" : "D"); // ToDo: Checl LOV
        month = getIntMonthForString(seasonMonth);
        baseUrl += "0"; //"&firstRegistrationMonth=" + month;
        baseUrl += "&firstRegistrationYear=" + fregYear;
        baseUrl += "&power=" + power;
        baseUrl += "&action=calc&modelSize=light";

        WDUtil.getWebDriver().get(baseUrl);
        System.out.println(baseUrl);
        /*
        makeId = getMakeForId(9);
        year = fregYear;
*/
    }

    @Given("^Test data API light \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void testDataAPILight(String natCode, String predict, String articleId, String makemodelId, String makemodel, String created, String pricePublicEuro, String bodyTypeId, String fuelId, String gearingId, String mileage, String power, String customer, String seasonMonth, String fregYear, String testdata) throws Throwable {

        String month;
        String baseUrl = WDUtil.getWebDriver().getCurrentUrl() + "priceestimation/estimate?";

        //String baseUrl = "https://www.autoscout24.be/priceestimation/estimate?featurebee=price-range-with-medium=true|TATSU-997-mixed-models=true";
        baseUrl += "&makeId=" + makemodelId.substring(0, makemodelId.indexOf("_"));
        baseUrl += "&modelId=" + makemodelId.substring(makemodelId.indexOf("_") + 1);
        baseUrl += "&bodyTypeId=" + bodyTypeId;
        baseUrl += "&mileage=" + mileage;
        month = getIntMonthForString(seasonMonth);
        baseUrl += "&firstRegistrationMonth=" + month;
        baseUrl += "&firstRegistrationYear=" + fregYear;
        baseUrl += "&fuelId=" + fuelId; //((fuelId.equals("B")) ? "ep.eid[121]=121" : "D"); // ToDo: Checl LOV
        baseUrl += "&transmissionId=" + gearingId;
        baseUrl += "&power=" + power;
        baseUrl += "&sellerType.name=" + customer.toLowerCase();

        System.out.println(baseUrl);
        WDUtil.getWebDriver().get(baseUrl);
    }

//    @Given("^Test data full model \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    @Given("^Test data full model \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void testDataFullModel( String predict, String article_id, String makemodel_id, String makemodel, String created, String price_public_euro, String body_type_id, String emission_class_id, String fuel_id, String gearing_id, String body_color_id, String mileage, String doors, String power, String customer, String equipment_id_1, String equipment_id_2, String equipment_id_3, String equipment_id_4, String equipment_id_5, String equipment_id_6, String equipment_id_9, String equipment_id_10, String equipment_id_12, String equipment_id_13, String equipment_id_15, String equipment_id_16, String equipment_id_17, String equipment_id_18, String equipment_id_19, String equipment_id_20, String equipment_id_21, String equipment_id_23, String equipment_id_26, String equipment_id_27, String equipment_id_29, String equipment_id_30, String equipment_id_31, String equipment_id_32, String equipment_id_33, String equipment_id_34, String equipment_id_37, String equipment_id_38, String equipment_id_39, String equipment_id_40, String equipment_id_41, String equipment_id_42, String equipment_id_43, String equipment_id_45, String equipment_id_46, String equipment_id_47, String equipment_id_49, String equipment_id_50, String equipment_id_51, String equipment_id_52, String equipment_id_53, String equipment_id_54, String equipment_id_110, String equipment_id_112, String equipment_id_113, String equipment_id_114, String equipment_id_115, String equipment_id_116, String equipment_id_117, String equipment_id_118, String equipment_id_119, String equipment_id_120, String equipment_id_121, String equipment_id_122, String equipment_id_123, String equipment_id_124, String equipment_id_125, String equipment_id_126, String equipment_id_127, String equipment_id_128, String equipment_id_129, String equipment_id_130, String equipment_id_131, String equipment_id_132, String consumption, String season_month, String freg_year, String testdate) throws Throwable {
        //Given Test data full model       "<predict>"     "<article_id>" "<makemodel_id>" "<makemodel>" "<created>" "<price_public_euro>" "<body_type_id>"                 "<emission_class_id>"        "<fuel_id>"         "<gearing_id>" "<body_color_id>"   "<mileage>" "<doors>" "<power>" "<customer>"                   "<equipment_id_1>" "<equipment_id_2>" "<equipment_id_3>"                 "<equipment_id_4>" "<equipment_id_5>" "<equipment_id_6>" "<equipment_id_9>" "<equipment_id_10>" "<equipment_id_12>" "<equipment_id_13>" "<equipment_id_15>" "<equipment_id_16>" "<equipment_id_17>" "<equipment_id_18>" "<equipment_id_19>" "<equipment_id_20>"                                                "<equipment_id_21>" "<equipment_id_23>" "<equipment_id_26>" "<equipment_id_27>" "<equipment_id_29>" "<equipment_id_30>" "<equipment_id_31>" "<equipment_id_32>"                                    "<equipment_id_33>" "<equipment_id_34>" "<equipment_id_37>" "<equipment_id_38>" "<equipment_id_39>" "<equipment_id_40>"                                                               "<equipment_id_41>" "<equipment_id_42>" "<equipment_id_43>" "<equipment_id_45>" "<equipment_id_46>" "<equipment_id_47>" "<equipment_id_49>" "<equipment_id_50>" "<equipment_id_51>" "<equipment_id_52>" "<equipment_id_53>" "<equipment_id_54>"                      "<equipment_id_110>" "<equipment_id_112>" "<equipment_id_113>" "<equipment_id_114>" "<equipment_id_115>" "<equipment_id_116>" "<equipment_id_117>" "<equipment_id_118>" "<equipment_id_119>" "<equipment_id_120>" "<equipment_id_121>" "<equipment_id_122>" "<equipment_id_123>" "<equipment_id_124>" "<equipment_id_125>" "<equipment_id_126>" "<equipment_id_127>" "<equipment_id_128>" "<equipment_id_129>" "<equipment_id_130>" "<equipment_id_131>" "<equipment_id_132>" "<consumption>" "<season_month>" "<freg_year>" "<testdate>"

        String baseUrl = "https://www.autoscout24.be/evaluationvoiture/resultat?";
        String makeId; String month; String year; String model; String fuel; String powerForm; String equipmentline; String mileageForm;

        baseUrl += "makeId=" + makemodel_id.substring(0, makemodel_id.indexOf("_"));
        baseUrl += "&mileage=" + mileage;
        //baseUrl += "&natCode=" + "10111370" ;// Audi 1 2ter"10117856"; 2002// Check
        baseUrl += "&natCode=" + "65544" ;// Peugeot Belgien
        baseUrl += "&ep.doors=" + "5";

        //baseUrl += "&equipmentLine=" + "xxxx1.8+T+quattro"; // Check
        baseUrl += "&equipmentLine=" + "1.4+HDi+Urban"; // Check
        baseUrl += "&vehicleGroupId=" + makemodel_id.substring(makemodel_id.indexOf("_")+1) + "_" + body_type_id + "_5"; // ToDo: Check doors!
        baseUrl += "&fuelId=" + fuel_id; //((fuelId.equals("B")) ? "ep.eid[121]=121" : "D"); // ToDo: Checl LOV
        month = getIntMonthForString(season_month);
        baseUrl += "&firstRegistrationMonth=" + month;
        baseUrl += "&firstRegistrationYear=" + freg_year;
        baseUrl += "&power=" + power;
        baseUrl += "&ep.bodyColorId=" + body_color_id;
        baseUrl += "&ep.emissionClassId=" + emission_class_id;
        baseUrl += "&ep.consumption=" + consumption;
        baseUrl += "&action=calc&modelSize=full";

        if (equipment_id_1.equals("1")) baseUrl += "&ep.eid[1]=1";
        if (equipment_id_2.equals("1")) baseUrl += "&ep.eid[2]=2";
        if (equipment_id_3.equals("1")) baseUrl += "&ep.eid[3]=3";
        if (equipment_id_4.equals("1")) baseUrl += "&ep.eid[4]=4";
        if (equipment_id_5.equals("1")) baseUrl += "&ep.eid[5]=5";
        if (equipment_id_6.equals("1")) baseUrl += "&ep.eid[6]=6";
        if (equipment_id_9.equals("1")) baseUrl += "&ep.eid[9]=9";
        if (equipment_id_10.equals("1")) baseUrl += "&ep.eid[10]=10";
        if (equipment_id_12.equals("1")) baseUrl += "&ep.eid[12]=12";
        if (equipment_id_13.equals("1")) baseUrl += "&ep.eid[13]=13";
        if (equipment_id_15.equals("1")) baseUrl += "&ep.eid[15]=15";
        if (equipment_id_16.equals("1")) baseUrl += "&ep.eid[16]=16";
        if (equipment_id_17.equals("1")) baseUrl += "&ep.eid[17]=17";
        if (equipment_id_18.equals("1")) baseUrl += "&ep.eid[18]=18";
        if (equipment_id_19.equals("1")) baseUrl += "&ep.eid[19]=19";

        if (equipment_id_20.equals("1")) baseUrl += "&ep.eid[20]=20";
        if (equipment_id_21.equals("1")) baseUrl += "&ep.eid[21]=21";
        if (equipment_id_23.equals("1")) baseUrl += "&ep.eid[23]=23";
        if (equipment_id_26.equals("1")) baseUrl += "&ep.eid[26]=26";
        if (equipment_id_27.equals("1")) baseUrl += "&ep.eid[27]=27";
        if (equipment_id_29.equals("1")) baseUrl += "&ep.eid[29]=29";

        if (equipment_id_30.equals("1")) baseUrl += "&ep.eid[30]=30";
        if (equipment_id_31.equals("1")) baseUrl += "&ep.eid[31]=31";
        if (equipment_id_32.equals("1")) baseUrl += "&ep.eid[32]=32";
        if (equipment_id_34.equals("1")) baseUrl += "&ep.eid[34]=34";
        if (equipment_id_37.equals("1")) baseUrl += "&ep.eid[37]=37";
        if (equipment_id_38.equals("1")) baseUrl += "&ep.eid[38]=38";
        if (equipment_id_39.equals("1")) baseUrl += "&ep.eid[39]=39";

        if (equipment_id_40.equals("1")) baseUrl += "&ep.eid[40]=40";
        if (equipment_id_41.equals("1")) baseUrl += "&ep.eid[41]=41";
        if (equipment_id_42.equals("1")) baseUrl += "&ep.eid[42]=42";
        if (equipment_id_43.equals("1")) baseUrl += "&ep.eid[43]=43";
        if (equipment_id_45.equals("1")) baseUrl += "&ep.eid[45]=45";
        if (equipment_id_46.equals("1")) baseUrl += "&ep.eid[46]=46";
        if (equipment_id_47.equals("1")) baseUrl += "&ep.eid[47]=47";
        if (equipment_id_49.equals("1")) baseUrl += "&ep.eid[49]=49";

        if (equipment_id_50.equals("1")) baseUrl += "&ep.eid[50]=50";
        if (equipment_id_51.equals("1")) baseUrl += "&ep.eid[51]=51";
        if (equipment_id_52.equals("1")) baseUrl += "&ep.eid[52]=52";
        if (equipment_id_53.equals("1")) baseUrl += "&ep.eid[53]=53";
        if (equipment_id_54.equals("1")) baseUrl += "&ep.eid[54]=54";

        if (equipment_id_110.equals("1")) baseUrl += "&ep.eid[110]=110";
        if (equipment_id_112.equals("1")) baseUrl += "&ep.eid[112]=112";
        if (equipment_id_113.equals("1")) baseUrl += "&ep.eid[113]=113";
        if (equipment_id_114.equals("1")) baseUrl += "&ep.eid[114]=114";
        if (equipment_id_115.equals("1")) baseUrl += "&ep.eid[115]=115";
        if (equipment_id_116.equals("1")) baseUrl += "&ep.eid[116]=116";
        if (equipment_id_117.equals("1")) baseUrl += "&ep.eid[117]=117";
        if (equipment_id_118.equals("1")) baseUrl += "&ep.eid[118]=118";
        if (equipment_id_119.equals("1")) baseUrl += "&ep.eid[119]=119";

        if (equipment_id_120.equals("1")) baseUrl += "&ep.eid[120]=120";
        if (equipment_id_121.equals("1")) baseUrl += "&ep.eid[121]=121";
        if (equipment_id_122.equals("1")) baseUrl += "&ep.eid[122]=122";
        if (equipment_id_123.equals("1")) baseUrl += "&ep.eid[123]=123";
        if (equipment_id_124.equals("1")) baseUrl += "&ep.eid[124]=124";
        if (equipment_id_125.equals("1")) baseUrl += "&ep.eid[125]=125";
        if (equipment_id_126.equals("1")) baseUrl += "&ep.eid[126]=126";
        if (equipment_id_127.equals("1")) baseUrl += "&ep.eid[127]=127";
        if (equipment_id_128.equals("1")) baseUrl += "&ep.eid[128]=128";
        if (equipment_id_129.equals("1")) baseUrl += "&ep.eid[129]=129";
        if (equipment_id_130.equals("1")) baseUrl += "&ep.eid[130]=130";
        if (equipment_id_131.equals("1")) baseUrl += "&ep.eid[131]=131";
        if (equipment_id_132.equals("1")) baseUrl += "&ep.eid[132]=132";

        WDUtil.getWebDriver().get(baseUrl);
        System.out.println(baseUrl);
    }

    // Convert "June" to 06
    private String getIntMonthForString(String seasonMonth) throws ParseException {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("MMM", Locale.ENGLISH).parse(seasonMonth));
        int monthInt = cal.get(Calendar.MONTH) + 1;
        String month = ((monthInt < 10) ? "0" : "") + monthInt;
        return month;
    }
}

