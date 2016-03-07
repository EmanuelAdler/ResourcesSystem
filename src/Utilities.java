import user.*;
import resource.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.annotation.Resources;

public class Utilities {
	
	ArrayList<User> userList = new ArrayList<User>();
	ArrayList<Resource> resourcesList = new ArrayList<Resource>();
	ArrayList<Allocation> allocationList = new ArrayList<Allocation>();
	Scanner sc = new Scanner(System.in);
	
	public void mainUtility(){
		System.out.println("--- Sistema de Gestao de Recursos ---");
		System.out.println("Escolha uma opcao");
		System.out.println("1. Adicionar usuario");
		System.out.println("2. Adicionar recurso");
		System.out.println("3. Alocar recurso");
		System.out.println("4. Procurar usuario");
		System.out.println("5. Procurar recurso");
		System.out.println("6. Verificar lista de alocacoes");
		System.out.println("7. Sair");
		
		int option = sc.nextInt();
		
		switch (option) {
		case 1:
			addUser();
			break;
		case 2:
			addResource();
			break;
		case 3:
			String aux = sc.nextLine();
			System.out.println("Insira o nome do usuario:");
			String name = sc.nextLine();
			newAllocation(name);
			break;
		case 4:
			System.out.println("Insira o nome do usu�rio:");
			aux = sc.nextLine();
			name = sc.nextLine();
			searchUser(name);
			break;
		case 5:
			System.out.println("Insira o tipo de recurso:");
			System.out.println("1. Sala de aula");
			System.out.println("2. Auditorio");
			System.out.println("3. Laboratorio");
			System.out.println("4. Projetor");
			int type = sc.nextInt();
			if(type == 1)
				searchResource("Classroom");
			else if(type == 2)
				searchResource("Auditorium");
			else if(type == 3)
				searchResource("Laboratory");
			else if(type == 4)
				searchResource("Projector");
			break;
		case 6:
			getAllAllocations();
			break;
		case 7:
			System.exit(0);
			break;
		default:
			System.out.println("Opcao invalida");
			break;
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
			createClassroom();
			break;
		case 4:
			createProjector();
			break;
		default:
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
		Activity newActivity = new Activity("", "", "");
		for(int i = 0;i < userList.size(); i++){
			if(userList.get(i).getName().equalsIgnoreCase(name)){
				if(userList.get(i) instanceof Professor){
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
						break;
					}
				}
				else
					newActivity.setTitle("apresentacao");
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
		
		System.out.println("Escolha o recurso:");
		System.out.println("1. Laboratorio");
		System.out.println("2. Sala de aula");
		System.out.println("3. Auditorio");
		System.out.println("4. Projetor");
		
		int option = sc.nextInt();
		switch (option) {
		case 1:
			for(int i = 0; i < resourcesList.size(); i++){
				if(resourcesList.get(i) instanceof Laboratory && resourcesList.get(i).getAllocated() == false){
					Allocation newAllocation = new Allocation(startDate, endDate, resourcesList.get(i).getId());
					allocationList.add(newAllocation);
					resourcesList.get(i).setAllocated(true);
					for(int j = 0;j < userList.size(); j++){
						if(userList.get(j).getName().equalsIgnoreCase(name)){
							newAllocation.userlist.add(userList.get(j));
							userList.get(j).getResourcesList().add(resourcesList.get(i));
							return;
							}
						}
				}
				else if(i > 0 && i == resourcesList.size()-1)
					System.out.println("Nao ha laboratorios disponiveis");
				return;
			}
			break;
		case 2:
			for(int i = 0; i < resourcesList.size(); i++){
				if(resourcesList.get(i) instanceof Classroom && resourcesList.get(i).getAllocated() == false){
					Allocation newAllocation = new Allocation(startDate, endDate, resourcesList.get(i).getId());
					allocationList.add(newAllocation);
					resourcesList.get(i).setAllocated(true);
					for(int j = 0;j < userList.size(); j++){
						if(userList.get(j).getName().equalsIgnoreCase(name)){
							newAllocation.userlist.add(userList.get(j));
							userList.get(j).getResourcesList().add(resourcesList.get(i));
							return;
							}
						}
				}
				else if(i > 0 && i == resourcesList.size()-1)
					System.out.println("Nao ha salas de aula disponiveis");
				return;
			}
			break;
		case 3:
			for(int i = 0; i < resourcesList.size(); i++){
				if(resourcesList.get(i) instanceof Auditorium && resourcesList.get(i).getAllocated() == false){
					Allocation newAllocation = new Allocation(startDate, endDate, resourcesList.get(i).getId());
					allocationList.add(newAllocation);
					resourcesList.get(i).setAllocated(true);
					for(int j = 0;j < userList.size(); j++){
						if(userList.get(j).getName().equalsIgnoreCase(name)){
							newAllocation.userlist.add(userList.get(j));
							userList.get(j).getResourcesList().add(resourcesList.get(i));
							return;
							}
						}
				}
				else if(i > 0 && i == resourcesList.size()-1)
					System.out.println("Nao ha auditorios disponiveis");
				return;
			}
			break;
		case 4:
			for(int i = 0; i < resourcesList.size(); i++){
				if(resourcesList.get(i) instanceof Projector){
					Allocation newAllocation = new Allocation(startDate, endDate, resourcesList.get(i).getId());
					allocationList.add(newAllocation);
					for(int j = 0;j < userList.size(); j++){
						if(userList.get(j).getName().equalsIgnoreCase(name)){
							newAllocation.userlist.add(userList.get(j));
							userList.get(j).getResourcesList().add(resourcesList.get(i));
							return;
							}
						}
				}
				else if(i > 0 && i == resourcesList.size()-1)
					System.out.println("Nao ha projetores disponiveis");
				return;
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
					if(resourcesList.get(i).getAllocated())
						result = result + "Diponivel: Nao\n";
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
					if(resourcesList.get(i).getAllocated())
						result = result + "Diponivel: Nao\n";
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
					if(resourcesList.get(i).getAllocated())
						result = result + "Diponivel: Nao\n";
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
					if(resourcesList.get(i).getAllocated())
						result = result + "Diponivel: Nao\n";
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
					result = result + "Id: " + resourcesList.get(j).getId() + "\n";
					System.out.println(result);
				}
			}
		}
	}
}