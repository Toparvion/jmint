/*****************************************
 * Имитация задержки в загрузке операций *
 *    из RBS                             *
 * Приложение: UPC-2                     *
 * Добавлен в версии: 2.70               *
 *****************************************/

/** Инструментируемый класс (FQ-name) */
//droplet class: rbs.RBSAdapterImpl

/** Инструментируемый метод */
//droplet method: loadRbsOpers

/** Точка вкрапления (одна из: BEFORE, INSTEAD, AFTER) */
//droplet cutpoint: AFTER

/** Текст вкрапления (адаптированный Java-код) */
//droplet text:
{
  Long faultyContractId = 10000L;
  if ($1 == faultyContractId) {
    long delay = (long) (3500 + Math.random()*1000);
    Thread.sleep(delay);
  }
}