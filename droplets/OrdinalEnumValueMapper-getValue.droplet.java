/*****************************************
 * Логирование внутри Hibernate
 * Приложение: UPC-2
 * Добавлен в версии: 2.70.01
 * Добавлен по задаче: UPCSEC-3193
 *****************************************/

/** Инструментируемый класс (FQ-name) */
//droplet class: org.hibernate.type.EnumType$OrdinalEnumValueMapper

/** Инструментируемый метод */
//droplet method: getValue

/** Точка вкрапления (одна из: BEFORE, INSTEAD, AFTER) */
//droplet cutpoint: BEFORE

/** Текст вкрапления (адаптированный Java-код) */
//droplet text:
{
  org.slf4j.LoggerFactory.getLogger("DROPPER").warn("names[0]: {}; rs[names[0]]: {}",
        $2[0], $1.getObject($2[0]));
}