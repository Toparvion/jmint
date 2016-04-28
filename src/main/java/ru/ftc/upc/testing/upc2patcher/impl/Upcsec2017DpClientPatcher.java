package ru.ftc.upc.testing.upc2patcher.impl;

import javassist.CtClass;
import javassist.CtMethod;
import ru.ftc.upc.testing.upc2patcher.ClassPatcher;

import static java.lang.String.format;

/**
 * Created by Plizga on 28.04.2016 12:27
 */
public class Upcsec2017DpClientPatcher implements ClassPatcher {

  private static final String DP_CLIENT_CLASS_SLASHED_NAME = "dp/DPClientImpl";
  private static final String PATIENT_METHOD_NAME = "fetchTransferStatus";

  @Override
  public boolean accept(String classFqName) {
    return DP_CLIENT_CLASS_SLASHED_NAME.equals(classFqName);
  }

  @Override
  public byte[] patch(CtClass class2patch) throws Exception {
    CtMethod patient = class2patch.getDeclaredMethod(PATIENT_METHOD_NAME);
    patient.setBody(INJECTION);
    byte[] modifiedClassCode = class2patch.toBytecode();
    System.out.println(format("Method '%s' has been patched.", patient.getLongName()));
    return modifiedClassCode;
  }

  private static final String INJECTION =
          "{\n" +
          "  String statusStr = null;\n" +
          "  try {\n" +
          "    java.util.Properties stub = new java.util.Properties();\n" +
          "    stub.load(new java.io.FileReader(System.getProperty(\"user.dir\") + java.io.File.separator + \"dp-edit-mock.properties\"));\n" +
          "    statusStr = stub.getProperty($1);\n" +
          "    if (statusStr != null) log.warn(\"For oID={} transfer status {} was taken from mock.\", $1, statusStr);\n" +
          "  } catch (java.io.IOException e) {\n" +
          "    log.error(\"Failed to load mocked transfer edit statuses.\", e);\n" +
          "  }\n" +
          "  if (statusStr == null) {\n" +
          "    dp.models.QuickPay quickPay = dp.models.QuickPay.infoService();\n" +
          "    dp.models.ReqTransferSearch req = new dp.models.ReqTransferSearch();\n" +
          "    req.setOID($1);\n" +
          "    quickPay.getInfoService().setReqTransferSearch(req);\n" +
          "    quickPay = sendQuickPay(quickPay, false, false);\n" +
          "    statusStr = quickPay.getInfoService().getAnsTransferSearch().getTransferStatus();" +
          "  }\n" +
          "  return statusStr;\n" +
          "}";

}
