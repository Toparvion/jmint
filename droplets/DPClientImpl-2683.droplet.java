// Полное имя класса, который необходимо модифицировать
//droplet class: dp.DPClientImpl

// Имя модифицируемого метода
//droplet method: currencyRate

// Точка подключения, одна из: BEFORE, AFTER, INSTEAD
//droplet cutpoint: INSTEAD

// Собственно вставляемый код
//droplet text:
{
  try {
    java.io.FileReader stubReader = new java.io.FileReader(System.getProperty("user.dir")
        + java.io.File.separator + "AnsCurrencyRate.xml");
    javax.xml.bind.Unmarshaller unmarshaller = quickPayJbcDoc.createUnmarshaller();
    dp.models.QuickPay quickPay = (dp.models.QuickPay) unmarshaller.unmarshal(stubReader);
    log.info("Данные курсов валют были загружены из файла AnsCurrencyRate.xml.");
    stubReader.close();
    return quickPay.getInfoService().getAnsCurrencyRate();

  } catch (Exception e) {
    log.error("Не удалось загрузить курсы валют из файла. Будет предпринят поход в ДП.", e);
    dp.models.QuickPay quickPay = dp.models.QuickPay.infoService();
    quickPay.getInfoService().setReqCurrencyRate($1);
    quickPay = sendQuickPay(quickPay, false, false);
    return quickPay.getInfoService().getAnsCurrencyRate();
  }
}