package Persistence;

import Business.Ativos.Acao;
import Business.Negociador;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateBD {
    private static void createRelations(Connection c) {
        Statement s = null;
        try {
            s = c.createStatement();

            s.executeUpdate("drop table if exists Ativo cascade;");
            s.executeUpdate("create table Ativo (Id varchar primary key, Nome varchar, ValorPorUnidade float);");

            s.executeUpdate("drop table if exists Acao cascade;");
            s.executeUpdate("create table Acao (Id int primary key, Empresa varchar);");

            s.executeUpdate("drop table if exists AcaoAtivo cascade;");
            s.executeUpdate("create table AcaoAtivo (IdAcao int references Acao(id), IdAtivo varchar references Ativo(id), primary key (IdAcao, IdAtivo));");


            s.executeUpdate("drop table if exists Indice cascade;");
            s.executeUpdate("create table Indice (Id int primary key, NumEmpresas int);");

            s.executeUpdate("drop table if exists IndiceAtivo cascade;");
            s.executeUpdate("create table IndiceAtivo (IdIndice int references Indice(id), IdAtivo varchar references Ativo(id), primary key (IdIndice, IdAtivo));");


            s.executeUpdate("drop table if exists Moeda cascade;");
            s.executeUpdate("create table Moeda (Id int primary key, MoedaA varchar, MoedaB varchar);");

            s.executeUpdate("drop table if exists MoedaAtivo cascade;");
            s.executeUpdate("create table MoedaAtivo (IdMoeda int references Moeda(id), IdAtivo varchar references Ativo(id), primary key (IdMoeda, IdAtivo));");

            s.executeUpdate("drop table if exists Negociador cascade;");
            s.executeUpdate("create table Negociador(Nif int primary key, Nome varchar, Email varchar, Password varchar, Saldo float);");

            s.executeUpdate("drop table if exists CFD cascade;");
            s.executeUpdate("create table CFD (Id int primary key, Data timestamp, UnidadesDeAtivo float," +
                    "ValorPorUnidadeNaCompra float, LimiteSup float, LimiteInf float, " +
                    "IdAtivo varchar references ativo(id) on delete cascade, NifNegociador int references Negociador(nif)" +
                    ", Aberto bit, ValorPorUnidadeNoFim float);");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void populateRelations(Connection c) {
        Statement s = null;
        try {
            s = c.createStatement();
            s.executeUpdate("insert into negociador values('274129914', 'Luís Alves', 'luismig.alves@gmail.com', '12345', 0)");
            s.executeUpdate("insert into ativo values('AAPL', 'Apple Inc.', 82.3);");
            s.executeUpdate("insert into acao values(1, 'Apple');");
            s.executeUpdate("insert into acaoativo values(1, 'AAPL');");
            s.executeUpdate("insert into CFD values(1, '2008-01-01 00:00:01', 2, 5, null, null, 'AAPL', 274129914, b'1', null);");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Connection c = Connect.connect();
        if (c == null) {
            System.out.println("Can't connect!");
            return;
        }
        createRelations(c);
        populateRelations(c);
        Acao a = new Acao("AAPL", "Apple Inc.", 82.3, "Apple");
        a.run();

        Connect.close(c);
    }

}
