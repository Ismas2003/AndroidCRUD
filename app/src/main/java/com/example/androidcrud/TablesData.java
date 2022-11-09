package com.example.androidcrud;

import com.example.androidcrud.tables.Operations;
import com.example.androidcrud.tables.OperationsTypes;
import com.example.androidcrud.tables.Patients;
import com.example.androidcrud.tables.Staff;
import com.example.androidcrud.tables.Wards;

import java.util.List;

public class TablesData {
    public void fillTables() {

        List<Operations> operationsCheck = MainActivity.db.operationsDao().getAll();
        List<Staff> staffCheck = MainActivity.db.staffDao().getAllForAdmin();
        List<Wards> wardsCheck = MainActivity.db.wardsDao().getAll();
        List<Patients> patientsCheck = MainActivity.db.patientsDao().getAll();
        List<OperationsTypes> operationsTypesCheck = MainActivity.db.operationsTypesDao().getAll();

        if (staffCheck.isEmpty()){
            MainActivity.db.staffDao().insertAll(new Staff("Яков","Фокин",
                    "Парфеньевич",4,"OZyMO",
                    "1354d851295a02b6eb5a0a58d9987ab0a49e0861067e71c932f564125e5a25b1e6b1d7ae7153f2fb0e7fc78347af5650c78ee82e9a5b13bde218a00121b16b87"));
            MainActivity.db.staffDao().insertAll(new Staff("Гарри","Ильин" ,
                    "Авдеевич",5,"QIpEG",
                    "68cb76d7c603ba76e1bd3c0060a18fd1db472f41272dac364fe2bf8243010042e1f6265ba390553e3e8a9e0a8e1c8ab9dde44274d3c74d55e27f2d1b320f252c"));
            MainActivity.db.staffDao().insertAll(new Staff("Леонид","Третьяков",
                    "Ефимович",2,"HdwAw",
                    "df869d369074b2f78a95e3d59fb94915dbb2f6f2b8f5ca071975ea7a156e3c6fc996e24c0a627068eaa55bd5974a08149c655421cb8c238c621954c3759c5cb6"));
            MainActivity.db.staffDao().insertAll(new Staff("Игнатий","Одинцов",
                    "Семенович",1,"DAafe",
                    "e6addf267bcf06a2acb285a526149c3d3df50d5010aee71ae50a88374437b0c15d39defdc73a79907c0d04a891a42537a51b21076c625451c5bf8fc0d6c6866a"));
            MainActivity.db.staffDao().insertAll(new Staff("Анатолий","Игнатьев",
                    "Михаилович",3,"Djwdm",
                    "c4f0c98d0238fb571722c92fa3fe44a2390ec6439f6ada4bcae45f28aee163a3898e06f34b357a1d9fd29783edf35bc75df0fa94a8cd680d12ed0c052838a441"));
            MainActivity.db.staffDao().insertAll(new Staff("Владимир","Асанов",
                    "Валерьевич",2,"Administrator",
                    "1bee03396cc6c1c645b61c6ac87a941e7abf5e42ce5b6ca5927b2013362756ffb4934326eba570ca34cd4521e5ac02a31dd9fcd1bd11268a7345ded443da1dd6"));
        }
        if (operationsTypesCheck.isEmpty()){
            MainActivity.db.operationsTypesDao().insertAll(new OperationsTypes("Экстренные",
                    "Производятся немедленно после постановки диагноза. Цель — спасение жизни пациента"));
            MainActivity.db.operationsTypesDao().insertAll(new OperationsTypes("Срочные",
                    "Производятся в течение 24 часов после постановки диагноза. Цель — уменьшение тяжести последствий операции"));
            MainActivity.db.operationsTypesDao().insertAll(new OperationsTypes("Плановые",
                    "Выполняются после полной предоперационной подготовки в то время, которое удобно из организационных соображений"));
        }
        if (wardsCheck.isEmpty()){
            MainActivity.db.wardsDao().insertAll(new Wards(15,1));
            MainActivity.db.wardsDao().insertAll(new Wards(15,2));
            MainActivity.db.wardsDao().insertAll(new Wards(15,3));
        }
        if (patientsCheck.isEmpty()){
            MainActivity.db.patientsDao().insertAll(new Patients("Василий","Коршунов","Иванович","Ленина 155",1));
            MainActivity.db.patientsDao().insertAll(new Patients("Константин","Ботанов","Петрович","Кавказская 29",1));
            MainActivity.db.patientsDao().insertAll(new Patients("Игорь","Камазов","Леонидович","Кавказская 37",2));
            MainActivity.db.patientsDao().insertAll(new Patients("Иван","Белов","Сергеевич","Ленина 134",2));
            MainActivity.db.patientsDao().insertAll(new Patients("Сергей","Кимашев","Сергеевич","Ленина 225",3));
        }
        if (operationsCheck.isEmpty()){
            MainActivity.db.operationsDao().insertAll(new Operations("Лечение паховой грыжи",
                    "Операция может быть полостной или лапороскопической. Во время вмешательства врачи по необходимости вправляют выпячивание и ушивают паховый канал",
                    3,1));
            MainActivity.db.operationsDao().insertAll(new Operations("Лечение желчнокаменной болезни",
                    "Если в результате обследования установлено, что в желчном пузыре образовались камни, может назначить плановую операцию по удалению желчного пузыря",
                    3, 2));
            MainActivity.db.operationsDao().insertAll(new Operations("Лечение острого аппендицита",
                    "Эта операция позволяет произвести визуальный осмотр и провести санацию органов брюшной полости",
                    1, 3));
            MainActivity.db.operationsDao().insertAll(new Operations("Лечение перфоративной язвы",
                    "Во время операции врачи удаляют пораженную часть желудка, а затем ушивают рану",
                    1, 4));
            MainActivity.db.operationsDao().insertAll(new Operations("Лечение механической желтухи",
                    "При механической желтухе операция носит многоуровневый характер",
                    2, 5));
            MainActivity.db.operationsDao().insertAll(new Operations("Лечение острой кишечной инфекции",
                    "При хирургическом вмешательстве удаляется препятствие. Проводится санация органов брюшной полости",
                    2, 6));
        }
    }
}
