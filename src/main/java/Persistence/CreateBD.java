package Persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateBD {
    public static void main(String[] args) {
        Connection c = Connect.connect();
        if (c == null)
            System.out.println("Can't connect!");

        Statement s = null;
        try {
            s = c.createStatement();
            
            s.executeUpdate("drop table if exists Ativo cascade;");
            s.executeUpdate("create table Ativo (Id int primary key, Nome varchar, ValorPorUnidade float);");

            s.executeUpdate("drop table if exists Acao cascade;");
            s.executeUpdate("create table Acao (Id int primary key, Empresa varchar);");

            s.executeUpdate("drop table if exists AcaoAtivo cascade;");
            s.executeUpdate("create table AcaoAtivo (IdAcao int references Acao(id), IdAtivo int references Ativo(id), primary key (IdAcao, IdAtivo));");


            s.executeUpdate("drop table if exists Indice cascade;");
            s.executeUpdate("create table Indice (Id int primary key, NumEmpresas int);");

            s.executeUpdate("drop table if exists IndiceAtivo cascade;");
            s.executeUpdate("create table IndiceAtivo (IdIndice int references Indice(id), IdAtivo int references Ativo(id), primary key (IdIndice, IdAtivo));");


            s.executeUpdate("drop table if exists Moeda cascade;");
            s.executeUpdate("create table Moeda (Id int primary key, MoedaA varchar, MoedaB varchar);");

            s.executeUpdate("drop table if exists MoedaAtivo cascade;");
            s.executeUpdate("create table MoedaAtivo (IdMoeda int references Moeda(id), IdAtivo int references Ativo(id), primary key (IdMoeda, IdAtivo));");

            s.executeUpdate("drop table if exists Negociador cascade;");
            s.executeUpdate("create table Negociador(Nif int primary key, Nome varchar, Email varchar, Password varchar, Saldo float);");

            s.executeUpdate("drop table if exists CFD cascade;");
            s.executeUpdate("create table CFD (Id int primary key, Data timestamp, UnidadesDeAtivo float," +
                    "ValorPorUnidadeNaCompra float, LimiteSup float, LimiteInf float, IdAtivo int references ativo(id) on delete cascade, NifNegociador int references Negociador(nif)" +
                    ", Aberto bit);");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Connect.close(c);
    }

}
