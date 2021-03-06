import user.*;
import resource.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.annotation.Resources;

public class Utilities {
	
	ArrayList<User> userList = new ArrayList<User>();
	ArrayList<Resource> resourcesList = new ArrayList<Resource>();
	ArrayList<Allocation> allocationList = new ArrayList<Allocation>();
	Scanner sc = new Scanner(System.in);
	
	public boolean mainUtility(){
		System.out.println("--- Sistema de Gestao de Recursos ---");
		System.out.println("Escolha uma opcao");
		System.out.println("1. Adicionar usuario");
		System.out.println("2. Adicionar recurso");
		System.out.println("3. Alocar recurso");
		System.out.println("4. Adicionar usuario a uma alocacao");
		System.out.println("5. Procurar usuario");
		System.out.println("6. Procurar recurso");
		System.out.println("7. Verificar lista e status de alocacoes");
		System.out.println("8. Atualizar status de alocacoes");
		System.out.println("9. Confirmar alocacao");
		System.out.println("10. Relatorio da unidade academica");
		System.out.println("11. Sair");
		
		int option;
		String optionAux = sc.nextLine();
		try {
			option = Integer.parseInt (optionAux);
		} catch (NumberFormatException e) {
		    System.out.println("Opcao invalida");
		    return false;
		}
		
		switch (option) {
		case 1:
			addUser();
			return false;
		case 2:
			addResource();
			return false;
		case 3:
			if(userList.size() == 0){
				System.out.println("Nao ha usuarios cadastrados");
				return false;
			}
			if(resourcesList.size() == 0){
				System.out.println("Nao ha recursos cadastrados");
				return false;
			}
			System.out.println("Insira o nome do usuario:");
			String name = sc.nextLine();
			newAllocation(name);
			return false;
		case 4:
			System.out.println("Insira o nome do usuario:");
			name = sc.nextLine();
			if(userList.size() == 0)
				System.out.println("Usuario nao encontrado");
			else
				addUserToAllocation(name);
			return false;
		case 5:
			System.out.println("Insira o nome do usuario:");
			name = sc.nextLine();
			if(userList.size() == 0)
				System.out.println("Usuario nao encontrado");
			else
				searchUser(name);
			return false;
		case 6:
			System.out.println("Insira o tipo de recurso:");
			System.out.println("1. Sala de aula");
			System.out.println("2. Auditorio");
			System.out.println("3. Laboratorio");
			System.out.println("4. Projetor");
			int type;
			String typeAux = sc.nextLine();
			try {
				type = Integer.parseInt (typeAux);
			} catch (NumberFormatException e) {
			    System.out.println("Opcao invalida");
			    return false;
			}
			
			if(type == 1)
				searchResource("Classroom");
			else if(type == 2)
				searchResource("Auditorium");
			else if(type == 3)
				searchResource("Laboratory");
			else if(type == 4)
				searchResource("Projector");
			return false;
		case 7:
			getAllAllocations();
			return false;
		case 8:
			updateStatus();
			return false;
		case 9:
			confirmAllocation();
			return false;
		case 10:
			relatory();
			return false;
		case 11:
			System.exit(0);
			return true;
		default:
			System.out.println("Opcao invalida");
			return false;
		}
	}
	
	public void addUser(){
		System.out.println("--- Sistema de Gestao de Recursos ---");
		System.out.println("Escolha uma opcao");
		System.out.println("1. Estudante");
		System.out.println("2. Professor");
		System.out.println("3. Pesquisador");
		
		int option = sc.nextInt();
		String aux;
		
		switch (option) {
		case 1:
			aux = sc.nextLine();
			createStudent();
			break;
		case 2:
			aux = sc.nextLine();
			createProfessor();
			break;
		case 3:
			aux = sc.nextLine();
			createResearcher();
			break;
		default:
			System.out.println("Opcao invalida");
			addUser();
			break;
		}
		
	}
	
	public void createResearcher(){
		System.out.println("--- Sistema de Gestao de Recursos ---");
		System.out.println("Nome: ");
		String name = sc.nextLine();
		System.out.println("Email: ");
		String email = sc.nextLine();
		Researcher newUser = new Researcher(name, email);
		userList.add(newUser);
		System.out.println("Usuario adicionado com sucesso");
		mainUtility();
	}
	
	public void createStudent() {
		System.out.println("--- Sistema de Gestao de Recursos ---");
		
		System.out.println("Nome:");
		String name = sc.nextLine();
		
		System.out.println("Email");
		String email = sc.nextLine();
		
		System.out.println("Escolha uma opcao");
		System.out.println("1. Graduacao");
		System.out.println("2. Mestrado");
		System.out.println("3. Doutorado");
		int  type = sc.nextInt();
		String aux = sc.nextLine();
		
		Student newUser;
		
		switch (type) {
		case 1:
			newUser = new Student(name, email, "graduacao");
			userList.add(newUser);
			break;
		case 2:
			newUser = new Student(name, email, "mestrado");
			userList.add(newUser);
			break;
		case 3:
			newUser = new Student(name, email, "doutorado");
			userList.add(newUser);
			break;
		default:
			System.out.println("Opcao invalida");
			createStudent();
			break;
		}
	}
	
	public void createProfessor() {
		System.out.println("--- Sistema de Gestao de Recursos ---");
		
		System.out.println("Nome:");
		String name = sc.nextLine();
		
		System.out.println("Email");
		String email = sc.nextLine();
		
		Professor newUser = new Professor(name, email);
		userList.add(newUser);
		System.out.println("Usuario criado com sucesso");
	}
	
	public void addResource(){
		System.out.println("--- Sistema de Gestao de Recursos ---");
		System.out.println("Escolha uma opcao");
		System.out.println("1. Laboratorio");
		System.out.println("2. Auditorio");
		System.out.println("3. Sala de aula");
		System.out.println("4. Projetor");
		
		int option = sc.nextInt();
		String aux;
		
		switch (option) {
		case 1:
			aux = sc.nextLine();
			createLab();
			break;
		case 2:
			aux = sc.nextLine();
			createAuditorium();
			break;
		case 3:
			aux = sc.nextLine();
			createClassroom();
			break;
		case 4:
			aux = sc.nextLine();
			createProjector();
			break;
		default:
			System.out.println("Opcao invalida");
			addResource();
			break;
		}
		mainUtility();
	}
	
	public void createLab(){
		int id = resourcesList.size();
		Laboratory newLab = new Laboratory(id);
		resourcesList.add(newLab);
		System.out.println("Laboratorio adicionado com sucesso. ID: " + newLab.getId());
	}
	
	public void createClassroom(){
		int id = resourcesList.size();
		Classroom newClass = new Classroom(id);
		resourcesList.add(newClass);
		System.out.println("Sala de aula adicionada com sucesso. ID: " + newClass.getId());
	}
	public void createAuditorium(){
		int id = resourcesList.size();
		Auditorium newAuditorium = new Auditorium(id);
		resourcesList.add(newAuditorium);
		System.out.println("Auditorio adicionado com sucesso. ID: " + newAuditorium.getId());
	}
	public void createProjector(){
		int id = resourcesList.size();
		Projector newProjector = new Projector(id);
		resourcesList.add(newProjector);
		System.out.println("Projetor adicionado com sucesso. ID: " + newProjector.getId());
	}
	
	public void newAllocation(String name){
		Activity newActivity = new Activity();
		for(int i = 0;i < userList.size(); i++){
			if(userList.get(i).getName().equalsIgnoreCase(name)){
				if(userList.get(i) instanceof Student){
					System.out.println("Vode nao tem permissao para iniciar a alocao de um recurso");
					return;
				}
				else if(userList.get(i) instanceof Professor){
					System.out.println("Escolha a atividade:");
					System.out.println("1. Aula");
					System.out.println("2. Laboratorio");
					System.out.println("3. Apresentacao");
					
					int option = sc.nextInt();
					switch (option) {
					case 1:
						newActivity.setTitle("aula");
						String aux = sc.nextLine();
						break;
					case 2:
						newActivity.setTitle("laboratorio");
						aux = sc.nextLine();
						break;
					case 3:
						newActivity.setTitle("apresentacao");
						aux = sc.nextLine();
						break;
					default:
						System.out.println("Opcao invalida");
						newAllocation(name);
						break;
					}
				}
				else if(userList.get(i) instanceof Researcher)
					newActivity.setTitle("apresentacao");
			}
			else if(i == userList.size()-1){
				System.out.println("Usuario nao encontrado");
				return;
			}
		}
		System.out.println("Descricao da atividade:");
		String description = sc.nextLine();
		System.out.println("Material de suporte:");
		String supportMaterial = sc.nextLine();
		newActivity.setDescription(description);
		newActivity.setSupportMaterial(supportMaterial);
		
		System.out.println("Data de inicio: (dd/mm/aaaa)");
		String startDate = sc.nextLine();
		
		System.out.println("Data de termino: (dd/mm/aaaa)");
		String endDate = sc.nextLine();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date startDate2 = null;
		Date endDate2 = null;
		try {
		    startDate2 = dateFormat.parse(startDate);
		    endDate2 = dateFormat.parse(endDate);
		} catch (ParseException e) {
		    System.out.println("Data inserida incorretamente");
		    return;
		}
		
		System.out.println("Escolha o recurso:");
		System.out.println("1. Laboratorio");
		System.out.println("2. Sala de aula");
		System.out.println("3. Auditorio");
		System.out.println("4. Projetor");
		
		int option = sc.nextInt();
		String aux = sc.nextLine();
		switch (option) {
		case 1:
			for(int i = 0; i < resourcesList.size(); i++){
				if(resourcesList.get(i) instanceof Laboratory && resourcesList.get(i).getAllocated() == false){
					Allocation newAllocation = new Allocation(startDate2, endDate2, resourcesList.get(i).getId());
					allocationList.add(newAllocation);
					resourcesList.get(i).setAllocated(true);
					for(int j = 0;j < userList.size(); j++){
						if(userList.get(j).getName().equalsIgnoreCase(name)){
							newAllocation.userlist.add(userList.get(j));
							userList.get(j).getResourcesList().add(resourcesList.get(i));
							for(int k = 0; k < userList.get(j).getResourcesList().size(); k++){
								if(userList.get(j).getResourcesList().get(k).getId() == resourcesList.get(i).getId())
								userList.get(j).getResourcesList().get(k).setActivity(newActivity);
							}
							return;
							}
						}
				}
				else if(i > 0 && i == resourcesList.size()-1)
					System.out.println("Nao ha laboratorios disponiveis");
			}
			break;
		case 2:
			for(int i = 0; i < resourcesList.size(); i++){
				if(resourcesList.get(i) instanceof Classroom && resourcesList.get(i).getAllocated() == false){
					Allocation newAllocation = new Allocation(startDate2, endDate2, resourcesList.get(i).getId());
					allocationList.add(newAllocation);
					resourcesList.get(i).setAllocated(true);
					for(int j = 0;j < userList.size(); j++){
						if(userList.get(j).getName().equalsIgnoreCase(name)){
							newAllocation.userlist.add(userList.get(j));
							userList.get(j).getResourcesList().add(resourcesList.get(i));
							for(int k = 0; k < userList.get(j).getResourcesList().size(); k++){
								if(userList.get(j).getResourcesList().get(k).getId() == resourcesList.get(i).getId())
								userList.get(j).getResourcesList().get(k).setActivity(newActivity);
							}
							return;
							}
						}
				}
				else if(i > 0 && i == resourcesList.size()-1)
					System.out.println("Nao ha salas de aula disponiveis");
			}
			break;
		case 3:
			for(int i = 0; i < resourcesList.size(); i++){
				if(resourcesList.get(i) instanceof Auditorium && resourcesList.get(i).getAllocated() == false){
					Allocation newAllocation = new Allocation(startDate2, endDate2, resourcesList.get(i).getId());
					allocationList.add(newAllocation);
					resourcesList.get(i).setAllocated(true);
					for(int j = 0;j < userList.size(); j++){
						if(userList.get(j).getName().equalsIgnoreCase(name)){
							newAllocation.userlist.add(userList.get(j));
							userList.get(j).getResourcesList().add(resourcesList.get(i));
							for(int k = 0; k < userList.get(j).getResourcesList().size(); k++){
								if(userList.get(j).getResourcesList().get(k).getId() == resourcesList.get(i).getId())
								userList.get(j).getResourcesList().get(k).setActivity(newActivity);
							}
							return;
							}
						}
				}
				else if(i > 0 && i == resourcesList.size()-1)
					System.out.println("Nao ha auditorios disponiveis");
			}
			break;
		case 4:
			for(int i = 0; i < resourcesList.size(); i++){
				if(resourcesList.get(i) instanceof Projector){
					Allocation newAllocation = new Allocation(startDate2, endDate2, resourcesList.get(i).getId());
					for(int j = 0;j < userList.size(); j++){
						if(userList.get(j).getName().equalsIgnoreCase(name)){
							newAllocation.userlist.add(userList.get(j));
							userList.get(j).getResourcesList().add(resourcesList.get(i));
							for(int k = 0; k < userList.get(j).getResourcesList().size(); k++){
								if(userList.get(j).getResourcesList().get(k).getId() == resourcesList.get(i).getId())
								userList.get(j).getResourcesList().get(k).setActivity(newActivity);
							}
							allocationList.add(newAllocation);
							return;
							}
						}
				}
				else if(i > 0 && i == resourcesList.size()-1)
					System.out.println("Nao ha projetores disponiveis");
			}
			break;
		default:
			break;
		}
		
	
	}
	
	public void searchUser(String userName) {
		for(int i = 0; i < userList.size(); i++){
			//System.out.println(userList.get(i).getName());
			if(userList.get(i).getName().equalsIgnoreCase(userName)){
				String result = "Nome: " + userName
						+ "\nEmail: " + userList.get(i).getEmail();
				if(userList.get(i).getResourcesList().size() == 0)
					result = result + "\nRecursos alocados: Nenhum";
				else{
					result = result + "\nRecursos alocados: ";
					
					for(int j = 0; j < userList.get(i).getResourcesList().size(); j++){
						for(int k = 0; k < resourcesList.size(); k++){
							if(resourcesList.get(k).getId() == userList.get(i).getResourcesList().get(j).getId()){
								if(resourcesList.get(k) instanceof Classroom)
									result = result + "Salad de aula\n";
								else if(resourcesList.get(k) instanceof Auditorium)
									result = result + "Auditorio\n";
								else if(resourcesList.get(k) instanceof Projector)
									result = result + "Projetor\n";
								else if(resourcesList.get(k) instanceof Laboratory)
									result = result + "Laboratorio\n";
							}
						}
					}
					
				}
				System.out.println(result);
			}
			else if(!userList.get(i).getName().equalsIgnoreCase(userName) && i == userList.size()-1){
				System.out.println("Usuario nao encontrado");
			}
		}
	}
	
	public void searchResource(String resourceName){
		String result;
		for(int i = 0; i < resourcesList.size(); i++){
			if(resourceName.equalsIgnoreCase("Classroom")){
				if(resourcesList.get(i) instanceof Classroom){
					result = "Sala de aula\n" + 
							"Id: " + resourcesList.get(i).getId() + "\n";
					if(resourcesList.get(i).getAllocated()){
						result = result + "Diponivel: Nao\n" + 
								"Atividade: " + resourcesList.get(i).getActivity().getTitle() + "\n" +
								"Descricao: " + resourcesList.get(i).getActivity().getDescription() + "\n";
					}
					else
						result = result + "Diponivel: Sim\n";
					int id = resourcesList.get(i).getId();
					for(int j = 0; j < allocationList.size(); j++){
						if(allocationList.get(j).getResourceId() == resourcesList.get(i).getId()){
							result = result + "Usuarios: ";
							for(int k = 0; k < allocationList.get(j).userlist.size(); k++){
								 result = result + allocationList.get(j).userlist.get(k).getName() + "\n";
							}
						}
					}
					System.out.println(result);
				}
			}
			else if(resourceName.equalsIgnoreCase("Auditorium")){
				if(resourcesList.get(i) instanceof Auditorium){
					result = "Auditorio\n" + 
							"Id: " + resourcesList.get(i).getId() + "\n";
					if(resourcesList.get(i).getAllocated()){
						result = result + "Diponivel: Nao\n" + 
								"Atividade: " + resourcesList.get(i).getActivity().getTitle() + "\n" +
								"Descricao: " + resourcesList.get(i).getActivity().getDescription() + "\n";
					}
					else
						result = result + "Diponivel: Sim\n";
					int id = resourcesList.get(i).getId();
					for(int j = 0; j < allocationList.size(); j++){
						if(allocationList.get(j).getResourceId() == resourcesList.get(i).getId()){
							result = result + "Usuarios: ";
							for(int k = 0; k < allocationList.get(j).userlist.size(); k++){
								 result = result + allocationList.get(j).userlist.get(k).getName() + "\n";
							}
						}
					}
					System.out.println(result);
				}
			}
			else if(resourceName.equalsIgnoreCase("Projector")){
				if(resourcesList.get(i) instanceof Projector){
					result = "Projetor\n" + 
							"Id: " + resourcesList.get(i).getId() + "\n";
					if(resourcesList.get(i).getAllocated()){
						result = result + "Diponivel: Nao\n" + 
								"Atividade: " + resourcesList.get(i).getActivity().getTitle() + "\n" +
								"Descricao: " + resourcesList.get(i).getActivity().getDescription() + "\n";
					}
					else
						result = result + "Diponivel: Sim\n";
					int id = resourcesList.get(i).getId();
					for(int j = 0; j < allocationList.size(); j++){
						if(allocationList.get(j).getResourceId() == resourcesList.get(i).getId()){
							result = result + "Usuarios: ";
							for(int k = 0; k < allocationList.get(j).userlist.size(); k++){
								 result = result + allocationList.get(j).userlist.get(k).getName() + "\n";
							}
						}
					}
					System.out.println(result);
				}
			}
			else if(resourceName.equalsIgnoreCase("Laboratory")){
				if(resourcesList.get(i) instanceof Laboratory){
					result = "Laboratorio\n" + 
							"Id: " + resourcesList.get(i).getId() + "\n";
					if(resourcesList.get(i).getAllocated()){
						result = result + "Diponivel: Nao\n" + 
								"Atividade: " + resourcesList.get(i).getActivity().getTitle() + "\n" +
								"Descricao: " + resourcesList.get(i).getActivity().getDescription() + "\n";
					}
					else
						result = result + "Diponivel: Sim\n";
					int id = resourcesList.get(i).getId();
					for(int j = 0; j < allocationList.size(); j++){
						if(allocationList.get(j).getResourceId() == resourcesList.get(i).getId()){
							result = result + "Usuarios: ";
							for(int k = 0; k < allocationList.get(j).userlist.size(); k++){
								 result = result + allocationList.get(j).userlist.get(k).getName() + "\n";
							}
						}
					}
					System.out.println(result);
				}
			}
			
			else if(i == resourcesList.size()-1){
				System.out.println("Recurso nao encontrado");
			}
		}
	}
	
	public void getAllAllocations(){
		String result = "";
		for(int i = 0; i < allocationList.size(); i++){
			for(int j = 0; j < resourcesList.size(); j++){
				if(allocationList.get(i).getResourceId() == resourcesList.get(j).getId()){
					if(resourcesList.get(j) instanceof Classroom)
						result = "Tipo: Sala de aula\n";
					else if(resourcesList.get(j) instanceof Auditorium)
						result = "Tipo: Auditorio\n";
					else if(resourcesList.get(j) instanceof Projector)
						result = "Tipo: Projetor\n";
					else if(resourcesList.get(j) instanceof Laboratory)
						result = "Tipo: Laboratorio\n";
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String parsedStartDate = dateFormat.format((allocationList.get(i).getStartDate()));
					String parsedEndDate = dateFormat.format((allocationList.get(i).getEndDate()));
					result = result + "Id: " + resourcesList.get(j).getId() + 
							"\nStatus: " + allocationList.get(i).getStatus() + 
							"\nData de inicio: " + parsedStartDate +
							"\nData de termino: " + parsedEndDate + 
							"\nAtividade: " + resourcesList.get(j).getActivity().getTitle() + 
							"\nDescrição: " + resourcesList.get(j).getActivity().getDescription();
					System.out.println(result);
				}
			}
		}
	}
	
	private void addUserToAllocation(String name) {
		for(int i = 0; i < userList.size(); i++){
			if(userList.get(i).getName().equalsIgnoreCase(name)){
				System.out.println("Escolha uma alocacao disponivel:");
				for(int j = 0; j < allocationList.size(); j++){
					for(int k = 0; k < resourcesList.size(); k++){
						if(allocationList.get(j).getResourceId() == resourcesList.get(k).getId()){
							String result;
							if(resourcesList.get(k) instanceof Classroom){
								System.out.println(j + ". Sala de aula\nId: " + resourcesList.get(k).getId());
							}
							else if(resourcesList.get(k) instanceof Laboratory){
								System.out.println(j + ". Laboratorio\nId: " + resourcesList.get(k).getId());
							}
							else if(resourcesList.get(k) instanceof Auditorium){
								System.out.println(j + ". Auditorio\nId: " + resourcesList.get(k).getId());
							}
							else if(resourcesList.get(k) instanceof Projector){
								System.out.println(j + ". Projetor\nId: " + resourcesList.get(k).getId());
							}
						}
					}
				}
				Scanner sc = new Scanner(System.in);
				int option = sc.nextInt();
				allocationList.get(option).userlist.add(userList.get(i));
				for(int k = 0; k < resourcesList.size(); k++){
					if(allocationList.get(option).getResourceId() == resourcesList.get(k).getId()){
						userList.get(i).resourcesList.add(resourcesList.get(k));
					}
				}
				
				return;
			}
		}
	}
	
	public void updateStatus(){
		String result = "";
		for(int i = 0; i < allocationList.size(); i++){
			if(allocationList.get(i).getStatus() == "em processo de alocacao"){
				allocationList.get(i).setStatus("alocado");
				for(int j = 0; j < resourcesList.size(); j++){
					if(resourcesList.get(j).getId() == allocationList.get(i).getResourceId()){
						if(resourcesList.get(i) instanceof Classroom)
							result = "Sala de aula\n";
						else if(resourcesList.get(i) instanceof Projector)
							result = "Projetor\n";
						else if(resourcesList.get(i) instanceof Auditorium)
							result = "Auditorio\n";
						else if(resourcesList.get(i) instanceof Laboratory)
							result = "Laboratorio\n";
						
						result = result + "Status: " + allocationList.get(i).getStatus() + "\n";						
						result = result + "Id: " + resourcesList.get(j).getId() + "\n";
						result = result + "Usuarios: ";
							for(int k = 0; k < allocationList.get(i).userlist.size(); k++){
								result = result + allocationList.get(i).userlist.get(k).getName() + "\n";
							}
					}
				}
			}
			if(allocationList.get(i).getStatus() == "em andamento"){
				allocationList.get(i).setStatus("concluido");
				for(int j = 0; j < resourcesList.size(); j++){
					if(resourcesList.get(j).getId() == allocationList.get(i).getResourceId()){
						if(resourcesList.get(i) instanceof Classroom)
							result = "Sala de aula\n";
						else if(resourcesList.get(i) instanceof Projector)
							result = "Projetor\n";
						else if(resourcesList.get(i) instanceof Auditorium)
							result = "Auditorio\n";
						else if(resourcesList.get(i) instanceof Laboratory)
							result = "Laboratorio\n";
						
						result = result + "Status: " + allocationList.get(i).getStatus() + "\n";						
						result = result + "Id: " + resourcesList.get(j).getId() + "\n";
						result = result + "Usuarios: ";
							for(int k = 0; k < allocationList.get(i).userlist.size(); k++){
								result = result + allocationList.get(i).userlist.get(k).getName() + "\n";
							}
					}
				}
			}
			
			System.out.println(result);
		}
	}
	
	public void confirmAllocation(){
		System.out.println("Insira o nome do usuario");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		
		for(int i = 0; i < userList.size(); i++){
			if(userList.get(i).getName().equalsIgnoreCase(name)){
				for(int k = 0; k < userList.get(i).getResourcesList().size(); k++){
					if(!confirmEmAndamento(name)){
						for(int j = 0; j < allocationList.size(); j++){
							if(userList.get(i).getResourcesList().get(k).getId() == allocationList.get(j).getResourceId() && allocationList.get(j).getStatus().equalsIgnoreCase("alocado")){
								allocationList.get(j).setStatus("em andamento");
							}
						}
					}else{
						System.out.println("Este usuario ja possui 2 recursos 'em andamento'");
						return;
					}
				}
				
			}
		}
	}
	
	public boolean confirmEmAndamento(String name){
		int quantity = 0;
		for(int j = 0; j < userList.size(); j++){
			if(userList.get(j).getName().equalsIgnoreCase(name)){
				for(int i = 0; i < userList.get(j).getResourcesList().size(); i++){
					for(int k = 0; k < allocationList.size(); k++){
						if(userList.get(j).getResourcesList().get(i).getId() == allocationList.get(k).getResourceId()){
							if(allocationList.get(k).getStatus().equals("em andamento")){
								quantity++;
								if(quantity > 2)
									return true;
							}
						}
					}
				}
			}
		}

		return false;
	}
	
	public void relatory(){
		String result;
		int quantityEmAndamento = 0;
		int quantityAlocado = 0;
		int quantityEmProcesso = 0;
		int quantityConcluido = 0;
		
		for(int i = 0; i < allocationList.size(); i++){
			if(allocationList.get(i).getStatus().equalsIgnoreCase("em andamento"))
				quantityEmAndamento++;
			else if(allocationList.get(i).getStatus().equalsIgnoreCase("alocado"))
				quantityAlocado++;
			else if(allocationList.get(i).getStatus().equalsIgnoreCase("em processo de alocacao"))
				quantityEmProcesso++;
			else if(allocationList.get(i).getStatus().equalsIgnoreCase("concluido"))
				quantityConcluido++;
		}
		
		int quantityClass = 0;
		int quantityPresentation = 0;
		int quantityLab = 0;
		
		for(int i = 0; i < resourcesList.size(); i++){
			if(resourcesList.get(i).getActivity().getTitle().equalsIgnoreCase("aula"))
				quantityClass++;
			if(resourcesList.get(i).getActivity().getTitle().equalsIgnoreCase("laboratorio"))
				quantityLab++;
			if(resourcesList.get(i).getActivity().getTitle().equalsIgnoreCase("apresentacao"))
				quantityPresentation++;
		}
		
		result = "Quantidade de usuarios: " + userList.size()
			+ "\nQuantidade de recursos 'em andamento': " + quantityEmAndamento
			+ "\nQuantidade de recursos 'alocado': " + quantityAlocado
			+ "\nQuantidade de recursos 'em processo de alocacao': " + quantityEmProcesso
			+ "\nQuantidade de recursos 'concluido': " + quantityConcluido
			+ "\nTotal de alocacoes: " + allocationList.size()
			+ "\nQuantidade de aulas tradicionais: " + quantityClass
			+ "\nQuantidade de aulas em laboratorio: " + quantityLab
			+ "\nQuantidade de apresentacoes: " + quantityPresentation + "\n";
		
		System.out.println(result);
		
	}

}
