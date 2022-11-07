package com.example.androidcrud;

import com.example.androidcrud.tables.Doctors;
import com.example.androidcrud.tables.Operations;
import com.example.androidcrud.tables.OperationsTypes;
import com.example.androidcrud.tables.Patients;
import com.example.androidcrud.tables.Wards;

import java.util.List;

public class TablesData {
    public void fillTables() {

        List<Operations> operationsCheck = MainActivity.db.operationsDao().getAll();
        List<Doctors> doctorsCheck = MainActivity.db.doctorsDao().getAllForAdmin();
        List<Wards> wardsCheck = MainActivity.db.wardsDao().getAll();
        List<Patients> patientsCheck = MainActivity.db.patientsDao().getAll();
        List<OperationsTypes> operationsTypesCheck = MainActivity.db.operationsTypesDao().getAll();

        if (doctorsCheck.isEmpty()){
            MainActivity.db.doctorsDao().insertAll(new Doctors("Иван","Казанов",
                    "Игоревич",5,"kazanov",
                    "3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2"));
            MainActivity.db.doctorsDao().insertAll(new Doctors("Олег","Ураганов" ,
                    "Владимирович",3,"uraganov",
                    "fb131bc57a477c8c9d068f1ee5622ac304195a77164ccc2d75d82dfe1a727ba8d674ed87f96143b2b416aacefb555e3045c356faa23e6d21de72b85822e39fdd"));
            MainActivity.db.doctorsDao().insertAll(new Doctors("Владислав","Кузнецов",
                    "Константинович",7,"kuznetsov",
                    "bdc247a1a0e28a586ed40744d281993d519abe981aaef33277d4877d167e1150816e9723d068a59509991ed0cdd8c5cea0f9ecd0ef23664db7cb85db5a0dbe12"));
            MainActivity.db.doctorsDao().insertAll(new Doctors("Леонид","Рязанцев",
                    "Владиславович",9,"admin",
                    "4fcfd9010619e20c3f94a00d90503ea807bc0cb95c590fdfc6386af60531d16f7ce7473a5d06aff5ec0998895c66d53ce2d570479f94286c90f15d318b9cd992"));
            MainActivity.db.doctorsDao().insertAll(new Doctors("Владимир","Крючков",
                    "Олегович",4,"kryuchkov",
                    "5e3155774d39d97c5f9e17c108c2b3e0485a43ae34ebd196f61a6f8bf732ef71a49e5710594cfc7391db114edf99f5da3ed96ef1d6ca5e598e85f91bd41e7eeb"));
            MainActivity.db.doctorsDao().insertAll(new Doctors("Александр","Пушкин",
                    "Сергеевич",6,"pushkin",
                    "c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec"));
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
