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
        List<Doctors> doctorsCheck = MainActivity.db.doctorsDao().getAll();
        List<Wards> wardsCheck = MainActivity.db.wardsDao().getAll();
        List<Patients> patientsCheck = MainActivity.db.patientsDao().getAll();
        List<OperationsTypes> operationsTypesCheck = MainActivity.db.operationsTypesDao().getAll();

        if (doctorsCheck.isEmpty()){
            MainActivity.db.doctorsDao().insertAll(new Doctors("Иван","Казанов",
                    "Игоревич",5,"kazanov",
                    "89a4ad086b6963436c68450c28a8f7d03b03f6064e023a6f7aa598d3acb9a958eb01ad957bafe958de5218b14b6ae0bd19c6515ba8465527c5f085cbe6f5f44b"));
            MainActivity.db.doctorsDao().insertAll(new Doctors("Олег","Ураганов" ,
                    "Владимирович",3,"uraganov",
                    "8b3cb9839854c35fcd21f93d8c7fc63f991ca354dab4e9e80a74f3fc33856717909092a7bff0535f9288bd6945fbbc620f5fae5faf49460f597e3faec5137e4a"));
            MainActivity.db.doctorsDao().insertAll(new Doctors("Владислав","Кузнецов",
                    "Константинович",7,"kuznetsov",
                    "a83dbf931c0c7a8461fdce666c5e6b91c7ce083d56b86d14d1c2608dcbbc778b6399ae090a08eb3ab996b3e4bee914e4f92cd296c6d93ffed391e416d7fd3078"));
            MainActivity.db.doctorsDao().insertAll(new Doctors("Леонид","Рязанцев",
                    "Владиславович",9,"admin",
                    "c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec"));
            MainActivity.db.doctorsDao().insertAll(new Doctors("Владимир","Крючков",
                    "Олегович",4,"kryuchkov",
                    "e8beecc8eb1f2acdcb321e2f2138d001aab77badbca1fce3d23fecf7bbe67a95bfa0507ba458c547a5b2119d2d67aadc8c138237b093997332e3816e747994f1"));
            MainActivity.db.doctorsDao().insertAll(new Doctors("Александр","Пушкин",
                    "Сергеевич",6,"pushkin",
                    "b14361404c078ffd549c03db443c3fede2f3e534d73f78f77301ed97d4a436a9fd9db05ee8b325c0ad36438b43fec8510c204fc1c1edb21d0941c00e9e2c1ce2"));
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
                    1, 3));
            MainActivity.db.operationsDao().insertAll(new Operations("Лечение механической желтухи",
                    "При механической желтухе операция носит многоуровневый характер",
                    2, 5));
            MainActivity.db.operationsDao().insertAll(new Operations("Лечение острой кишечной инфекции",
                    "При хирургическом вмешательстве удаляется препятствие. Проводится санация органов брюшной полости",
                    2, 6));
        }
    }
}
