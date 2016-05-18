/*****************************************
 * Запрос лимитов по работающему
 * номеру счета
 * Приложение: UPC-2
 * Добавлен в версии: 2.71
 *****************************************/

/** Инструментируемый класс (FQ-name) */
//droplet class: ftc.pc.lim.service.LimitsSyncService

/** Инструментируемый метод */
//droplet method: synchronizeWithCalm

/** Точка вкрапления (одна из: BEFORE, INSTEAD, AFTER) */
//droplet cutpoint: BEFORE

/** Текст вкрапления (адаптированный Java-код) */
//droplet text:
{
    log.warn("AccountNum {} will be replaced with {}.", $1, "10000000000008142540");
    $1 = "10000000000008142540";
}