package ru.bspb.test;

import java.net.PortUnreachableException;

public class Finals {
    public static final String[] CARDS_POPOVER_HEADER_TEXT = {"КАРТЫ","БОНУСНАЯ ПРОГРАММА ЯРКО","ПОЛЕЗНОЕ"};
    public static final String[] CARDS_POPOVER_LINKS_TEXT = {
            "Дебетовые карты",
            "Кредитные карты",
            "Премиальные карты",
            "О программе",
            "Партнеры и акции",
            "Частые вопросы",
            "Интернет-банк",
            "Акции и скидки",
            "Частые вопросы",
            "Заявление о несогласии с операцией",
            "Информирование по карте",
            "Тарифы и условия обслуживания"
    };
    public static final String[] CARDS_POPOVER_LINKS = {
            "https://www.bspb.ru/retail/cards/debit",
            "https://www.bspb.ru/retail/cards/credit",
            "https://www.bspb.ru/retail/premium/premium-cards",
            "https://www.bspb.ru/yarko#about",
            "https://www.bspb.ru/yarko",
            "https://www.bspb.ru/yarko#yarko-faq",
            "https://www.bspb.ru/retail/ibank",
            "https://www.bspb.ru/retail/deals",
            "https://www.bspb.ru/retail/faq",
            "https://www.bspb.ru/retail/cards/nesoglasie-s-operatsiej",
            "https://www.bspb.ru/retail/cards/services/sms-service",
            "https://www.bspb.ru/retail/cards/docs"
    };
    public static final String[] CREDIT_POPOVER_HEADER_TEXT = {"ВСЕ КРЕДИТНЫЕ ПРОДУКТЫ", "ПОЛЕЗНОЕ"};
    public static final String[] CREDIT_POPOVER_LINKS_TEXT = {
            "Потребительский кредит",
            "Кредитные карты",
            "Автокредит",
            "Ипотека",
            "Кредитные каникулы",
            "Тарифы и условия обслуживания",
            "Частые вопросы"
    };
    public static final String[] CREDIT_POPOVER_LINKS = {
            "https://www.bspb.ru/retail/consumer-loan",
            "https://www.bspb.ru/retail/cards/credit",
            "https://www.bspb.ru/retail/car-loan",
            "https://www.bspb.ru/retail/mortgage",
            "https://www.bspb.ru/retail/kreditnie-kanikuly",
            "https://www.bspb.ru/retail/banking-services",
            "https://www.bspb.ru/retail/faq"
    };
    public static final String[] IPOTEC_POPOVER_HEADER_TEXT = {"ИПОТЕЧНЫЕ ПРОГРАММЫ","ОНЛАЙН-СЕРВИСЫ И РАСЧЕТЫ","ПОЛЕЗНОЕ"};
    public static final String[] IPOTEC_POPOVER_LINKS_TEXT = {
            "Семейная ипотека от 5,35%",
            "Господдержка 6,35%",
            "IT-ипотека",
            "Квартира в новостройке",
            "Готовое жилье",
            "Рефинансирование ипотеки",
            "Все ипотечные программы",
            "Заказ отчета об оценке",
            "Сервис электронной регистрации",
            "Эскроу-счета",
            "Аккредитивы",
            "Центры ипотечного кредитования",
            "Тарифы и условия обслуживания",
            "Сопровождение ипотеки",
            "Аккредитованные объекты",
            "Кредитные каникулы",
            "Анкеты, документы"
    };
    public static final String []  IPOTEC_POPOVER_LINKS = {
            "https://www.bspb.ru/retail/mortgage/family",
            "https://www.bspb.ru/retail/mortgage/gos-support",
            "https://www.bspb.ru/retail/mortgage/it",
            "https://www.bspb.ru/retail/mortgage/first/standart",
            "https://www.bspb.ru/retail/mortgage/second/kvartira",
            "https://www.bspb.ru/retail/mortgage/mortgagerefinance",
            "https://www.bspb.ru/retail/mortgage",
            "https://bspb-ipoteka.ru/",
            "https://www.bspb.ru/retail/bank-account/electro-registration",
            "https://www.bspb.ru/retail/bank-account/escrow-accounts",
            "https://www.bspb.ru/retail/bank-account/bill-of-credit",
            "https://www.bspb.ru/retail/mortgage/center",
            "https://www.bspb.ru/retail/banking-services",
            "https://www.bspb.ru/retail/mortgage/maintenance",
            "https://www.bspb.ru/retail/mortgage/accredited",
            "https://www.bspb.ru/retail/kreditnie-kanikuly",
            "https://www.bspb.ru/retail/mortgage/docs"
    };
    public static final String[] DEPOSIT_POPOVER_HEADER_TEXT = {"ПОДОБРАТЬ ВКЛАД","ДРУГИЕ ПРОДУКТЫ И УСЛУГИ","ПОЛЕЗНОЕ"};
    public static final String[] DEPOSIT_POPOVER_LINKS_TEXT = {
            "Все вклады",
            "Осень",
            "Валютный резерв",
            "Стратег",
            "Рантье",
            "Управляемый",
            "Вклады для пенсионеров",
            "Накопительный счет",
            "Банковские счета",
            "Сейфовые ячейки",
            "Интернет-банк",
            "Тарифы и условия обслуживания",
            "Частые вопросы"
    };
    public static final String[] DEPOSIT_POPOVER_LINKS = {
            "https://www.bspb.ru/retail/deposits",
            "https://www.bspb.ru/retail/deposits/osen",
            "https://www.bspb.ru/retail/deposits/valutniy-rezerv",
            "https://www.bspb.ru/retail/deposits/strateg",
            "https://www.bspb.ru/retail/deposits/rantie",
            "https://www.bspb.ru/retail/deposits/upravljaemyj",
            "https://www.bspb.ru/retail/pensionary/pensionary-deposits",
            "https://www.bspb.ru/retail/deposits/savings",
            "https://www.bspb.ru/retail/bank-account",
            "https://www.bspb.ru/retail/safes",
            "https://www.bspb.ru/retail/ibank",
            "https://www.bspb.ru/retail/banking-services",
            "https://www.bspb.ru/retail/faq"
    };
    public static final String[] TRANSACTION_POPOVER_HEADER_TEXT = {"ПОПУЛЯРНОЕ","ПОЛЕЗНОЕ"};
    public static final String[] TRANSACTION_POPOVER_LINKS_TEXT = {
            "Оплата услуг",
            "Автоплатежи и подписка",
            "Переводы",
            "Система быстрых платежей",
            "Интернет-банк",
            "Мобильное приложение",
            "Безопасность",
            "Частые вопросы",
            "Тарифы и условия обслуживания"
    };
    public static final String[] TRANSACTION_POPOVER_LINKS = {
            "https://www.bspb.ru/retail/payments/services",
            "https://www.bspb.ru/retail/payments/subscriptions",
            "https://www.bspb.ru/retail/payments/transfers",
            "https://www.bspb.ru/retail/payments/sbp",
            "https://www.bspb.ru/retail/ibank",
            "https://www.bspb.ru/retail/mobile",
            "https://www.bspb.ru/about/security",
            "https://www.bspb.ru/retail/faq",
            "https://www.bspb.ru/retail/banking-services"
    };
    public static final String[] OTHER_POPOVER_HEADER_TEXT = {"СПЕЦИАЛЬНОЕ ОБСЛУЖИВАНИЕ","ДРУГИЕ ПРОДУКТЫ И УСЛУГИ","ОНЛАЙН-СЕРВИСЫ"};
    public static final String[] OTHER_POPOVER_LINKS_TEXT = {
            "Самозанятым",
            "Зарплатным клиентам",
            "Индивидуальный зарплатный проект",
            "Пенсионное обслуживание",
            "Программа «Премиум»",
            "Программа «Премиум лайт»",
            "Банковские счета",
            "Инвестиционные продукты",
            "Залоговое имущество",
            "Страхование",
            "Интернет-банк",
            "Мобильное приложение",
            "Электронная подпись",
            "Биржевой мост",
            "Госуслуги"
    };
    public static final String[] OTHER_POPOVER_LINKS = {
            "https://www.bspb.ru/retail/self-employed",
            "https://www.bspb.ru/retail/salary",
            "https://www.bspb.ru/retail/salary/individual",
            "https://www.bspb.ru/retail/pensionary",
            "https://www.bspb.ru/retail/premium",
            "https://www.bspb.ru/retail/premium-light",
            "https://www.bspb.ru/retail/bank-account",
            "https://www.bspb.ru/retail/investment",
            "https://bspb.ru/retail/lien/",
            "https://www.bspb.ru/retail/insurance",
            "https://www.bspb.ru/retail/ibank",
            "https://www.bspb.ru/retail/mobile",
            "https://www.bspb.ru/retail/kep",
            "https://www.bspb.ru/retail/bridge",
            "https://www.bspb.ru/retail/gosuslugi"
    };
    public static final String[] PARTS_LINKS = {
            "",
            "",
            "",
            "",
            ""
    };
    public static final String MAIN_PAGE_URL = "https://www.bspb.ru/retail/ibank";
    public static final String LOGIN_PAGE_URL = "https://i.bspb.ru/auth?response_type=code&client_id=1&redirect_uri=https%3A%2F%2Fi.bspb.ru%2Flogin%2Fsuccess&prefetch_uri=https%3A%2F%2Fi.bspb.ru%2Flogin%2Fprefetch&force_new_session=true";
    public static final String LINK_WITH_BANK_PAGE_URL = "https://www.bspb.ru/feedback";
    public static final String BANKOMAT_PAGE_URL = "https://www.bspb.ru/map";
    public static final String HOMEPAGE_URL = "https://www.bspb.ru/";
}
