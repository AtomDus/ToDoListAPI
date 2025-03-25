package be.bdus.utils.initializer;

import be.bdus.entities.Task;
import be.bdus.entities.User;
import be.bdus.entities.enums.UserRole;
import be.bdus.repositories.TaskRepository;
import be.bdus.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(TaskRepository taskRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        loadUsers();
        loadTasks();
    }

    private void loadUsers() {
        if (userRepository.count() == 0) {
            String password = passwordEncoder.encode("password");

            User admin = new User("admin", "admin@email.com", password, UserRole.ADMIN);
            User organizer1 = new User("organizer", "organizer@email.com", password, UserRole.ORGANIZER);
            User participant1 = new User("participant", "participant@email.com", password, UserRole.PARTICIPANT);
            User organizer2 = new User("danj", "danj@email.com", password, UserRole.ORGANIZER);
            User participant2 = new User("julie", "julie@email.com", password, UserRole.PARTICIPANT);

            userRepository.saveAll(List.of(admin, organizer1, organizer2, participant1, participant2));
        }
    }

    private void loadTasks() {
        if (taskRepository.count() == 0) {
            Task t1 = new Task("Préparer réunion", "Créer l'ordre du jour pour la réunion de lundi");
            Task t2 = new Task("Nettoyer le bureau", "Ranger les documents et nettoyer le poste de travail");
            Task t3 = new Task("Envoyer rapport", "Envoyer le rapport mensuel à la direction");
            Task t4 = new Task("Mise à jour site web", "Mettre à jour les informations des événements sur le site");
            Task t5 = new Task("Répondre aux emails", "Traiter tous les emails en attente dans la boîte de réception");
            Task t6 = new Task("Créer support de formation", "Préparer le PowerPoint pour la formation des nouveaux employés");

            taskRepository.saveAll(List.of(t1, t2, t3, t4, t5, t6));
        }

    }

}