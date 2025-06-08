package com.example.dz1

import android.widget.Toast

class Game {
    val people:Player
    val computer:Player

    constructor(_people: Player, _computer: Player){
        people = _people
        computer = _computer
    }

    fun computerStep(){
        val rnds = (0 until 5).random()
        computer.step = variantSteps[rnds]
    }
    fun whoWin(): String {
        var massegeWin:String = "Никто не победил. Ничья"
        if(people.step =="Камень"){
            if(computer.step =="Ножницы"){
                people.countWin++;
                massegeWin = "Победил игрок "+people.name+". Камень разбивает ножницы"
                return massegeWin
            }
            if(computer.step =="Бумага"){
                computer.countWin++;
                massegeWin = "Победил игрок "+computer.name+". Бумага заворачивает камень"
                return massegeWin
            }
            if(computer.step =="Ящерица"){
                people.countWin++;
                massegeWin = "Победил игрок "+people.name+". Камень давит ящерицу"
                return massegeWin
            }
            if(computer.step =="Спок"){
                computer.countWin++;
                massegeWin = "Победил игрок "+computer.name+". Спок испаряет камень"
                return massegeWin
            }
        }
        if(people.step =="Ножницы"){
            if(computer.step =="Камень"){
                computer.countWin++;
                massegeWin = "Победил игрок "+computer.name+". Камень разбивает ножницы"
                return massegeWin
            }
            if(computer.step =="Бумага"){
                people.countWin++;
                massegeWin = "Победил игрок "+people.name+". Ножницы режут бумагу"
                return massegeWin
            }
            if(computer.step =="Ящерица"){
                people.countWin++;
                massegeWin = "Победил игрок "+people.name+". Ножницы отрезают голову ящерице"
                return massegeWin
            }
            if(computer.step =="Спок"){
                computer.countWin++;
                massegeWin = "Победил игрок "+computer.name+". Спок ломает ножницы"
                return massegeWin
            }
        }
        if(people.step =="Бумага"){
            if(computer.step =="Камень"){
                people.countWin++;
                massegeWin = "Победил игрок "+people.name+". Бумага заворачивает камень"
                return massegeWin
            }
            if(computer.step =="Ножницы"){
                computer.countWin++;
                massegeWin = "Победил игрок "+computer.name+". Ножницы режут бумагу"
                return massegeWin
            }
            if(computer.step =="Ящерица"){
                computer.countWin++;
                massegeWin = "Победил игрок "+computer.name+". Ящерица ест бумагу"
                return massegeWin
            }
            if(computer.step =="Спок"){
                people.countWin++;
                massegeWin = "Победил игрок "+people.name+". Бумага подставляет спока"
                return massegeWin
            }
        }
        if(people.step =="Ящерица"){
            if(computer.step =="Камень"){
                computer.countWin++;
                massegeWin = "Победил игрок "+computer.name+". Камень давит ящерицу"
                return massegeWin
            }
            if(computer.step =="Ножницы"){
                computer.countWin++;
                massegeWin = "Победил игрок "+computer.name+". Ножницы отрезают голову ящерицы"
                return massegeWin
            }
            if(computer.step =="Бумага"){
                people.countWin++;
                massegeWin = "Победил игрок "+people.name+". Ящерица ест бумагу"
                return massegeWin
            }
            if(computer.step =="Спок"){
                people.countWin++;
                massegeWin = "Победил игрок "+people.name+". Ящерица травит спока"
                return massegeWin
            }
        }
        if(people.step =="Спок"){
            if(computer.step =="Камень"){
                people.countWin++;
                massegeWin = "Победил игрок "+people.name+". Спок испаряет камень"
                return massegeWin
            }
            if(computer.step =="Ножницы"){
                people.countWin++;
                massegeWin = "Победил игрок "+people.name+". Спок ломает ножницы"
                return massegeWin
            }
            if(computer.step =="Ящерица"){
                computer.countWin++;
                massegeWin = "Победил игрок "+computer.name+". Ящерица травит спока"
                return massegeWin
            }
            if(computer.step =="Бумага"){
                computer.countWin++;
                massegeWin = "Победил игрок "+computer.name+". Бумага подставляет спока"
                return massegeWin
            }
        }
        return massegeWin
    }
}

var variantSteps = arrayOf("Камень", "Ножницы", "Бумага", "Ящерица", "Спок")