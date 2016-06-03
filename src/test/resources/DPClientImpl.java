package dp;

import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import dp.models.QuickPay;

/**
 * @author Toparvion
 */
public class DPClientImpl {

  /**
   * Some description.
   * @param reqCurrencyRate
   * @return current exchange rate
   *
   * @cutpoint INSTEAD
   */
  public QuickPay.AnsCurrencyRate currencyRate(QuickPay.ReqCurrencyRate reqCurrencyRate) {
    try {
      FileReader stubReader = new FileReader(System.getProperty("user.dir")
              + File.separator + "AnsCurrencyRate.xml");
      Unmarshaller unmarshaller = quickPayJbcDoc.createUnmarshaller();
      QuickPay quickPay = (QuickPay) unmarshaller.unmarshal(stubReader);
      log.info("Rates were loaded from file AnsCurrencyRate.xml.");
      stubReader.close();
      // int someQuickPayContainingId = 1;
      return quickPay.getInfoService().getAnsCurrencyRate();

    } catch (Exception e) {
      log.error("Unable to load rates from file. Falling back to real service.", e);
      dp.models.QuickPay quickPay = QuickPay.infoService();
      quickPay.getInfoService().setReqCurrencyRate($1);
      quickPay = sendQuickPay(quickPay, false, false);
      QuickPay.AnsCurrencyRate ans = quickPay.getInfoService().getAnsCurrencyRate();
      return ans;
    }
  }
}
