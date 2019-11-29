package persistence;

import business.ativos.*;

import java.sql.*;
import java.util.*;

public class AtivoDAO implements Map<String,Ativo>{


	@Override
	public int size() {
		Connection c = Connect.connect();
		if (c == null){
			System.out.println("Can't connect");
			return 0;
		}

		Statement s = null;
		int result = 0;

		try {

			s = c.createStatement();

			ResultSet resultSet = s.executeQuery("select count(*) from ativo;");
			resultSet.next();
			result = resultSet.getInt(1);
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		Connect.close(c);

		return result;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean containsKey(Object o) {
		Connection c = Connect.connect();
		if (c == null){
			System.out.println("Can't connect");
			return false;
		}

		if (!(o instanceof String))
			return false;

		String key = (String) o;

		PreparedStatement s = null;
		boolean result = false;

		try {
			s = c.prepareStatement("select id from ativo where id = ?");
			s.setString(1,key);

			ResultSet resultSet = s.executeQuery();
			result = resultSet.isBeforeFirst();
			resultSet.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		Connect.close(c);

		return result;
	}

	@Override
	public boolean containsValue(Object o) {
		return false;
	}

	private int sizeAtivoPorTipo(Connection c, String tipo) {

		Statement s;
		int result = 0;

		try {

			s = c.createStatement();

			ResultSet resultSet = s.executeQuery("select count(*) from " + tipo+";");
			resultSet.next();
			result = resultSet.getInt(1);
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}


	private String getClassAtivo( Connection c, String id){

		PreparedStatement s;
		String[] ativos = {"acao","indice","moeda","commodity"};

		try {
			for(int i = 0; i < ativos.length; i++) {
				s = c.prepareStatement("select * from " + ativos[i] +"ativo"+ " where idAtivo = ?;");
				s.setString(1,id);

				ResultSet resultSet = s.executeQuery();
				if(resultSet.isBeforeFirst())  {
					resultSet.close();
					return ativos[i];
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}


	private Acao getAcao(Connection c, String id, String nome, double valorPorUnidade){

	    PreparedStatement s;

	    try {
            s = c.prepareStatement("select * from acaoativo inner join acao ON acao.id = acaoativo.idacao where acaoativo.idativo = ?");
            s.setString(1,id);

            ResultSet resultSet = s.executeQuery();

            if (!resultSet.isBeforeFirst())
                return null;

            resultSet.next();
            String empresa = resultSet.getString("empresa");

            return (new Acao(id,nome,valorPorUnidade,empresa));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	    return null;
    }

    private Moeda getMoeda(Connection c, String id, String nome, double valorPorUnidade){

        PreparedStatement s;

        try {
            s = c.prepareStatement("select * from moedaativo inner join moeda ON moeda.id = moedaativo.idmoeda where moedaativo.idativo = ?");
            s.setString(1,id);

            ResultSet resultSet = s.executeQuery();

            if (!resultSet.isBeforeFirst())
                return null;

            resultSet.next();
            String moedaA = resultSet.getString("moedaa");
            String moedaB = resultSet.getString("moedab");

            return (new Moeda(id,nome,valorPorUnidade,moedaA,moedaB));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Indice getIndice(Connection c, String id, String nome, double valorPorUnidade){

        PreparedStatement s;

        try {
            s = c.prepareStatement("select * from indiceativo inner join indice ON indice.id = indiceativo.idindice where indiceativo.idativo = ?");
            s.setString(1,id);

            ResultSet resultSet = s.executeQuery();

            if (!resultSet.isBeforeFirst())
                return null;

            resultSet.next();
            int num_empresas = resultSet.getInt("numempresas");

            return (new Indice(id,nome,valorPorUnidade,num_empresas));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Commodity getCommodity(Connection c, String id, String nome, double valorPorUnidade){

        PreparedStatement s;

        try {
            s = c.prepareStatement("select * from commodityativo inner join commodity ON commodity.id = commodityativo.idcommodity where commodityativo.idativo = ?");
            s.setString(1,id);

            ResultSet resultSet = s.executeQuery();

            if (!resultSet.isBeforeFirst())
                return null;

            resultSet.next();
            String pais = resultSet.getString("pais");

            return (new Commodity(id,nome,valorPorUnidade,pais));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


	@Override
	public Ativo get(Object o) {

		Connection c = Connect.connect();
		if (c == null) {
			System.out.println("Can't connect!");
			return null;
		}

		if (!(o instanceof String))
			return null;

		String key = (String) o;

		Ativo ativo = null;

		PreparedStatement s;
		try {
			s = c.prepareStatement("select * from ativo where id = ?");
			s.setString(1,key);

			ResultSet resultSet = s.executeQuery();
			if (!resultSet.isBeforeFirst())
				return null;

			resultSet.next();
			String id = resultSet.getString("id");
			String nome = resultSet.getString("nome");
			double valorPorUnidade = resultSet.getDouble("valorporunidade");


			String classeAtivo = getClassAtivo(c,key);

			if (classeAtivo.equals("acao")) {
                return getAcao(c,id, nome, valorPorUnidade);
            }

			else if (classeAtivo.equals("moeda"))
                ativo = getMoeda(c,id, nome, valorPorUnidade);

			else if (classeAtivo.equals("indice"))
                ativo = getIndice(c,id, nome, valorPorUnidade);

			else if (classeAtivo.equals("commodity"))
                ativo = getCommodity(c,id, nome, valorPorUnidade);

			resultSet.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		Connect.close(c);

		return ativo;
	}

	private Ativo putAcao(Connection c, Ativo ativo){

		PreparedStatement s;

		try {
			int res;
			int id = sizeAtivoPorTipo(c,"acao");

			s = c.prepareStatement("insert into acao values (?,?);");
			s.setInt(1, id);
			s.setString(2, ((Acao) ativo).getEmpresa());
			s.executeUpdate();


			s = c.prepareStatement("insert into acaoativo values (?,?);");
			s.setInt(1, id);
			s.setString(2, ativo.getId());
			res = s.executeUpdate();

			if (res == 1)
				return ativo;

		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	private Ativo putMoeda(Connection c, Ativo ativo){

		PreparedStatement s;

		try {
			int res;
			int id = sizeAtivoPorTipo(c,"moeda");

			s = c.prepareStatement("insert into moeda values (?,?,?);");
			s.setInt(1, id);
			s.setString(2, ((Moeda) ativo).getMoedaA());
			s.setString(3, ((Moeda) ativo).getMoedaB());
			s.executeUpdate();

			s = c.prepareStatement("insert into moedaativo values (?,?);");
			s.setInt(1, id);
			s.setString(2, ativo.getId());
			res = s.executeUpdate();

			if (res ==1){
				return ativo;
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}


	private Ativo putIndice(Connection c, Ativo ativo){

		PreparedStatement s;

		try{
			int res;
			int id = sizeAtivoPorTipo(c,"indice");

			s = c.prepareStatement("insert into indice values (?,?);");
			s.setInt(1, id);
			s.setInt(2, ((Indice) ativo).getNumEmpresas());
			s.executeUpdate();

			s = c.prepareStatement("insert into indiceativo values (?,?);");
			s.setInt(1, id);
			s.setString(2, ativo.getId());
			res = s.executeUpdate();

			if (res ==1){
				return ativo;
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}

    private Ativo putCommodity(Connection c, Ativo ativo){

        PreparedStatement s;

        try{
            int res;
            int id = sizeAtivoPorTipo(c,"commodity");

            s = c.prepareStatement("insert into commodity values (?,?);");
            s.setInt(1, id);
            s.setString(2, ((Commodity) ativo).getPais());
            s.executeUpdate();

            s = c.prepareStatement("insert into commodityativo values (?,?);");
            s.setInt(1, id);
            s.setString(2, ativo.getId());
            res = s.executeUpdate();

            if (res ==1){
                return ativo;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


	@Override
	public Ativo put(String s, Ativo ativo) {

		Connection c = Connect.connect();
		if (c == null) {
			System.out.println("Can't connect!");
			return null;
		}

		if (!s.equals(ativo.getId()))
			return null;

		PreparedStatement st;
		try {

			if (this.containsKey(s)) {
				st = c.prepareStatement("update ativo set id = ?, nome = ?, valorporunidade = ? where id = ?;");
				st.setString(4,ativo.getId());

				st.setString(1,ativo.getId());
				st.setString(2,ativo.getNome());
				st.setDouble(3,ativo.getValorPorUnidade());

			}
			else {
				st = c.prepareStatement("insert into ativo values (?, ?, ?);");


				st.setString(1, ativo.getId());
				st.setString(2, ativo.getNome());
				st.setDouble(3, ativo.getValorPorUnidade());
			}

			st.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		Connect.close(c);

		return ativo;

	}

	@Override
	public Ativo remove(Object o) {
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Ativo> map) {

	}

	@Override
	public void clear() {

	}

	@Override
	public Set<String> keySet() {
		return null;
	}

	@Override
	public Collection<Ativo> values() {

		Connection c = Connect.connect();
		if (c == null){
			return null;
		}

		AtivoTipoDAO a = new AtivoTipoDAO();

		List<Ativo> ativos = new ArrayList<>();

		for(int i = 0; i<AtivoConsts.TOTAL_TIPOS_ATIVOS; i++) {

			ativos.addAll(a.get(AtivoConsts.ALL_ATIVOS[i]));
		}

		Connect.close(c);

		return ativos;
	}

	@Override
	public Set<Entry<String, Ativo>> entrySet() {
		return null;
	}
}