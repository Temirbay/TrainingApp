package com.example.trainingapp.exercises;

import com.example.trainingapp.R;
import com.example.trainingapp.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExercisesFabrique {

    public static List<Exercise> generateExercises() {
        List<Exercise> list = new ArrayList<>();
        list.add(new Exercise(
           "Планка с подъемом руки и ноги",
           "Прими упор лежа, спину держи прямой.\n" +
                   "Вытяни правую руку вперед и подними левую ногу. Вернись в исходное положение и повтори то же самое левой рукой и правой ногой.",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F0.gif?alt=media&token=dfe8a904-e83d-4fa0-8576-825e43ceef73"
        ));
        list.add(new Exercise(
                "Скалолаз",
                "Прими упор лежа, спину держи прямой.\n" +
                        "Подними правую ногу и подтяни ее колено к груди. Сразу же верни обратно и повтори то же левой – продолжай, быстро чередуя ноги.",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F1.gif?alt=media&token=a2b25539-e62d-48c7-80dc-6bb3d9cecf3e"
        ));


        list.add(new Exercise(
                "Жим для трицепса лежа на боку\n",
                "Ляг на правый бок, положив правую руку на левый. Левую ладонь поставь перед собой, немного согнув руку в локте.\n" +
                        "Разгибая левую руку (тут работает трицепс), отожмись от пола, поднимая корпус вверх. Потом перевернись на другой бок и выполняй такой же жим правой рукой.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F2.gif?alt=media&token=03f6486a-9ade-4347-b3c0-55750dee41ae"
        ));


        list.add(new Exercise(
                "«Складной нож» с подъемом таза\n",
                "Ляг на спину, вытянув руки по бокам.  Поставь правую стопу ближе к тазу, упрись левой рукой в пол; подними корпус и прямую левую ногу вверх и постарайся коснуться ее носка правой рукой. Затем опустись обратно на пол и повтори, поменяв стороны.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F2.gif?alt=media&token=03f6486a-9ade-4347-b3c0-55750dee41ae"
        ));

        list.add(new Exercise(
                "Перекат в «березку»\n",
                "Встань задом к коврику, затем ляг на спину, согнув ноги в коленях и вытянув руки вдоль корпуса. Дальше, приподнимая таз, заведи прямые ноги за голову (насколько позволяет растяжка). Опусти ноги и сразу же, отталкиваясь руками, встань обратно в исходную позицию.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F4.gif?alt=media&token=57d412a5-cc77-47d1-807f-bb2e8a809f56"
        ));

        list.add(new Exercise(
                "Подъем ног\n",
                "Ляг на спину, вытянув руки вдоль корпуса. Подними прямые ноги к потолку, образуя буку L. Опускай ноги обратно как можно медленнее, но следи за тем, чтобы не перегружать поясницу.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F5.gif?alt=media&token=69efd1c1-aa82-41c1-87b1-5c83ab7c47c1"
        ));

        list.add(new Exercise(
                "Сгибание ног\n",
                "Ляг на спину, положив стопы на фитбол; тело вытянуто в прямую линию. Сгибая ноги до прямого угла в коленях, подкати фитбол к себе. Напрягай пресс и ягодичные мышцы, чтобы удерживать мяч.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F6.gif?alt=media&token=cf7c1573-1ac9-4d11-90db-ebd44d32b4f3"
        ));

        list.add(new Exercise(
                "Повороты в стороны\n",
                "Ляг на фитбол спиной, согнув ноги в коленях. Сложи ладони вместе и вытяни руки перед собой. Поворачивай верх тела влево и вправо, стараясь удерживать положение таза.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F7.gif?alt=media&token=4c12ec9b-da5f-46cd-b9c5-7bdcab52f131"
        ));

        list.add(new Exercise(
                "Мостик",
                "Ляг на спину, согнув ноги в коленях и поставив пятки на фитбол. Подними таз над полом, чтобы корпус и бедра образовали прямую линию, и сделай паузу в мостике. Опусти таз обратно на пол.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F8.gif?alt=media&token=a5fd9357-118c-4a3f-9705-0841b35c6fae"
        ));

        list.add(new Exercise(
                "Боковые перескоки\n",
                "Поставь правую ногу на BOSU, руки сложи перед собой. Оттолкнувшись от снаряда опорной ногой, перепрыгни через него и поставь на него левую. Затем прыгай обратно.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F9.gif?alt=media&token=4b757527-b557-4bbc-a9d4-84d489bb9b6c"
        ));

        list.add(new Exercise(
                "Берпи",
                "Возьми перевернутую BOSU двумя руками и приложи круглой поверхностью к полу. Из упора сидя выброси ноги назад, приняв упор лежа, затем прыжком приставь их обратно. Сразу же встань и подними BOSU над головой.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F10.gif?alt=media&token=eb615282-7331-4764-acff-a2a652b1c84b"
        ));

        list.add(new Exercise(
                "Складка",
                "Сядь на BOSU, поставив стопы на пол. Отклонись назад, вытягивая ноги вперед, а затем скрутись обратно, подтягивая колени к груди.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F10.gif?alt=media&token=eb615282-7331-4764-acff-a2a652b1c84b"
        ));


        list.add(new Exercise(
                "Сгибания для бицепсов бедер\n",
                "Ляг на спину, положив руки по бокам и разместив пятки в петлях TRX. Подними таз над полом, вытянув тело в прямую линию. Затем подтяни колени к корпусу. Медленно разогни ноги, возвращаясь в исходное положение.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F12.gif?alt=media&token=1cb081df-2dba-4806-8e47-c1db1556bd5e"
        ));

        list.add(new Exercise(
                "Мостик",
                "Ляг на спину, вытянув руки по бокам и разместив пятки в петлях TRX (ноги согнуты в коленях примерно под прямым углом). Опираясь на ладони, подними таз над полом, чтобы корпус и бедра образовали прямую линию. В верхней точке сделай паузу на пару секунд, сокращая ягодичные мышцы, а затем опустись обратно.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F13.gif?alt=media&token=8bf910e6-abf9-453b-903d-a449b4228924"
        ));

        list.add(new Exercise(
                "Скручивания",
                "Ляг на спину, согнув ноги в коленях и просунув ноги в петли. Подними верх тела и вытяни руки к стопам. Сделай паузу на пару вдохов-выдохов и медленно опустись обратно.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F14.gif?alt=media&token=54a57178-a1ef-4027-899e-9a875fdab030"
        ));
        list.add(new Exercise(
                "Перекрестные скручивания\n",
                " Ляг на спину, просунув прямые ноги в петли TRX и держа ладони у головы. Поднимая корпус, подтяни к себе правое колено и попробуй дотронуться до него левым локтем. Затем повтори то же самое с левым коленом и правым локтем.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F15.gif?alt=media&token=fce33e31-154c-4875-b822-40fc9b148bf5"
        ));
        list.add(new Exercise(
                "Скручивания с касанием ног\n",
                "Ляг на спину с согнутыми ногами, поставив пятки на скамью. Подними корпус к ногам и вытяни левую руку, стараясь коснуться правого носка. Вернись в исходное положение, а затем повтори то же самое с правой рукой.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F16.gif?alt=media&token=d77fa843-6e01-4f92-9e42-03a1cfce4177"
        ));
        list.add(new Exercise(
                "Подъем ног на скамье\n",
                "Ляг спиной на скамью, вытянув ноги. Можешь взяться за нее, чтобы облегчить упражнение. Подними прямые ноги вверх, а затем приподними и таз. Опусти обратно и повтори.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F17.gif?alt=media&token=f4f4cc8c-2fe2-4cd0-9eef-a897010ef469"
        ));

        list.add(new Exercise(
                "Складка",
                "Сядь на скамью, держа согнутые в коленях ноги перед собой. Можешь взяться за скамейку руками, чтобы облегчить упражнение. Отклони корпус назад и выпрями ноги вперед. Напрягая мышцы кора, вернись обратно в исходное положение.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F18.gif?alt=media&token=69d968d9-7906-4e58-879d-a7eef17747c6"
        ));

        list.add(new Exercise(
                "Мостик",
                "Сядь на пол с согнутыми ногами, верхней частью спины опираясь на скамью. Сокращая ягодицы и бицепсы бедер, подними таз вверх, вытягивая тело от головы до коленей в прямую линию. Затем медленно опусти таз обратно к полу.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F19.gif?alt=media&token=47f70954-92df-4d7b-a6b5-f1250ea4dd7b"
        ));

        list.add(new Exercise(
                "Скручивания с гантелью\n",
                "Сядь на коврик с согнутыми ногами, держа гантель малого или среднего веса обеими руками. Затем ляг на спину, опустив руки за голову. Сначала медленно подними руки с гантелью вверх, а за ними – корпус, выполняя скручивание. Затем опустись в исходное положение.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F20.gif?alt=media&token=78442927-f0d4-4258-8269-30b0aee6961e"
        ));

        list.add(new Exercise(
                "«Складной нож» \n",
                "Возьми две легкие гантели и ляг на спину, вытянув руки за голову. Подними корпус и прямую левую ногу, гантелью в правой руке постарайся дотянуться до левой стопы. Опустись на коврик в исходное положение и повтори то же самое с правой ногой и левой рукой.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F21.gif?alt=media&token=dc5b7dfc-0971-42c6-a94f-7c43fca1b9aa"
        ));

        list.add(new Exercise(
                "Боковая планка «нитка и иголка»\n",
                " Встань в боковую планку на правом локте, вытянув левую руку с легкой гантелью вверх. Опусти левую руку и «продень» гантель в зазор между корпусом и полом. Затем вытяни ее обратно вверх. При этом удерживай положение таза и бедер, не прогибайся!\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F22.gif?alt=media&token=3e945849-9560-47c6-ae93-0938a491ed91"
        ));

        list.add(new Exercise(
                "Байдарочная тяга\n",
                "Возьми в руки легкую гантель и сядь на пол, немного отклонившись и держа согнутые ноги перед собой. Опусти гантель влево, поворачивая корпус, затем вправо – будто гребешь на байдарке.\n",
                "https://firebasestorage.googleapis.com/v0/b/trainingapp-336db.appspot.com/o/exercises%2F23.gif?alt=media&token=8bd22496-0dc0-4eaa-93fd-033eb92d513b"
        ));
        return list;
    }

}
