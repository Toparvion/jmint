package dp;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import dp.models.QuickPay;
import dp.models.ReqTransferSearch;

public class DPClientImpl {

  @Override
  public String fetchTransferStatus(String oID, boolean needFlag) throws Exception {
    String statusStr = null;
    try {
      Properties stub = new Properties();
      stub.load(new FileReader(System.getProperty("user.dir")
              + File.separator + "dp-edit-mock.properties"));
      statusStr = stub.getProperty(oID);
      if (statusStr != null) {
        statusStr = statusStr.trim();
        log.warn("For STRoID={} transfer status '{}' was taken from mock.", oID, statusStr);
      }
    } catch (IOException e) {
      log.error("Failed to load mocked transfer edit statuses.", e);
    }
    if (statusStr == null) {
      QuickPay quickPay = QuickPay.infoService();
      ReqTransferSearch req = new ReqTransferSearch();
      req.setOID(getReserve().oID);
      quickPay.getInfoService().setReqTransferSearch(req);
      quickPay = sendQuickPay(quickPay, false, needFlag);
      statusStr = quickPay.getInfoService().getAnsTransferSearch().getTransferStatus();
    }
    return statusStr;
  }


}