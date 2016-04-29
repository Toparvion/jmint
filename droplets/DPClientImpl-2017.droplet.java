// Полное имя класса, который необходимо модифицировать
//droplet class: dp.DPClientImpl

// Имя модифицируемого метода
//droplet method: fetchTransferStatus

// Точка подключения; одна из: BEFORE, AFTER, INSTEAD
//droplet cutpoint: INSTEAD

// Собственно вставляемый код
//droplet text:
{
  String statusStr = null;
  try {
    java.util.Properties stub = new java.util.Properties();
    stub.load(new java.io.FileReader(System.getProperty("user.dir") + java.io.File.separator + "dp-edit-mock.properties"));
    statusStr = stub.getProperty($1);
    if (statusStr != null) log.warn("For oID={} transfer status {} was taken from mock.", $1, statusStr);
  } catch (java.io.IOException e) {
    log.error("Failed to load mocked transfer edit statuses.", e);
  }
  if (statusStr == null) {
    dp.models.QuickPay quickPay = dp.models.QuickPay.infoService();
    dp.models.ReqTransferSearch req = new dp.models.ReqTransferSearch();
    req.setOID($1);
    quickPay.getInfoService().setReqTransferSearch(req);
    quickPay = sendQuickPay(quickPay, false, false);
    statusStr = quickPay.getInfoService().getAnsTransferSearch().getTransferStatus();
  }
  return statusStr;
} 