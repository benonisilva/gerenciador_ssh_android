package com.example.monografiassh2013.bd.dao;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * Classe que modela servidores usado no DAO(date access object)
 */
public class Servidor implements Parcelable{
	  private long id;
	  private String nome,host,user,pass;
	  private int porta;

	  public Servidor(Parcel in) {
		this.id = in.readLong();
		this.nome = in.readString();
		this.host = in.readString();
		this.user = in.readString();
		this.pass = in.readString();
		this.porta = in.readInt();
	}

	public Servidor() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeLong(this.id);
		dest.writeString(this.nome);
		dest.writeString(this.host);
		dest.writeString(this.user);
		dest.writeString(this.pass);
		dest.writeInt(this.porta);
		
		
	}
	
	public static final Parcelable.Creator<Servidor> CREATOR = new Parcelable.Creator<Servidor>() {
        public Servidor createFromParcel(Parcel in) {
            return new Servidor(in);
        }

        public Servidor[] newArray(int size) {
            return new Servidor[size];
        }
    };

	  
} 