package br.com.ufpb.lab2.services;

import br.com.ufpb.lab2.dtos.*;
import br.com.ufpb.lab2.entities.Comment;
import br.com.ufpb.lab2.entities.Course;
import br.com.ufpb.lab2.entities.Tag;
import br.com.ufpb.lab2.exceptions.*;
import br.com.ufpb.lab2.repositories.CommentRepository;
import br.com.ufpb.lab2.repositories.TagRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import br.com.ufpb.lab2.repositories.CourseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static br.com.ufpb.lab2.dtos.ReturnCommentDTO.returnCommentDTO;

@Service
@Transactional
public class DisciplinaService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private TagRepository tagRepository;

    @PostConstruct
    public void initDisciplinas() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Course>> typeReference = new TypeReference<>() {};

        try {
            InputStream inputStream = getClass().getResourceAsStream("/json/disciplinasSI.json");
            List<Course> courseList = mapper.readValue(inputStream, typeReference);
            this.courseRepository.saveAll(courseList);
            System.out.println("Disciplinas salvas no banco de dados");
        } catch (IOException e) {
            System.out.println("Não foi possível salvar as disciplinas: " + e.getMessage());
        }
    }

    public boolean isCourseListEmpty(List<Course> courseList) {
        if(courseList == null || courseList.size() == 0) {
            throw new NotRegisteredException("Banco de disciplinas vazio",
                    "Não existe nenhuma disciplina cadastrada");
        }
        return true;
    }

    public boolean isCourseFound(Optional<Course> optionalCourse) {
        if(!optionalCourse.isPresent()) {
            Long id = optionalCourse.get().getId();
            throw new CourseNotFoundException(String.format("A disciplina de id %d não foi encontrada", id),
                    "Disciplina não encontrada. Verifique se o id passado está correto ou se ela está cadastrada no banco");
        }
        return true;
    }

    public boolean isIdValid(String id) {
        try {
            if(id == null) {
                throw new InvalidIdException("ID invalido.", "O id da disciplina não pode ser nulo.");
            }

            Long longId = Long.parseLong(id);
            if (longId < 1) {
                throw new InvalidIdException("ID invalido.", "O id da disciplina é um valor positivo.");
            }
        } catch (NumberFormatException e) {
            throw new InvalidIdException("ID invalido", "O id passado não é um número ou não é um número inteiro");
        }
        return true;
    }

    public boolean isRateValid(Double rate) {
        if(rate == null || rate < 0 ) {
            throw new InvalidRateException("Nota inválida",
                    "Esta nota tem que ser um valor maior ou igual a zero e não pode ser nula");
        }
        return true;
    }

    public boolean isCommentValid(String comment) {
        if(comment == null || comment.isEmpty()) {
            throw new InvalidCommentException("Comentário inválido",
                    "Este comentário não pode ser nulo ou vazio");
        }
        return true;
    }

    public boolean isTagValid(String tag) {
        if(tag == null || tag.isEmpty()) {
            throw new InvalidTagException("Tag inválida",
                    "Esta tag não pode ser nula ou vazia");
        }
        return true;
    }

    public List<ReturnCourseDTO> returnAllCourses() {
        List<Course> courseList = courseRepository.findAll();
        isCourseListEmpty(courseList);

        List<ReturnCourseDTO> returnCourseDTOList = new ArrayList<>();
        for(Course course : courseList) {
            returnCourseDTOList.add(ReturnCourseDTO.from(course));
        }
        return returnCourseDTOList;
    }

    public SpecificCourseDTO returnSpecificCourse(String id) {
        isIdValid(id);
        Optional<Course> optionalCourse = courseRepository.findById(Long.parseLong(id));
        isCourseFound(optionalCourse);

        Course course = optionalCourse.get();
        return SpecificCourseDTO.from(course);
    }

    public AddLikeDTO addLike(String id) throws CourseNotFoundException {
        isIdValid(id);
        Optional<Course> optionalCourse = courseRepository.findById(Long.parseLong(id));
        isCourseFound(optionalCourse);

        Course course = optionalCourse.get();
        course.adicionaLike();
        courseRepository.save(course);
        return AddLikeDTO.from(course);
    }

    public AddRateDTO addRate(String id, Double nota) {
        isIdValid(id);
        isRateValid(nota);
        Optional<Course> optionalCourse = courseRepository.findById(Long.parseLong(id));
        isCourseFound(optionalCourse);

        Course course = optionalCourse.get();
        course.adicionaNota(nota);
        courseRepository.save(course);
        return AddRateDTO.from(course);
    }

    public AddCommentDTO addComment(String id, String commentText) {
        isIdValid(id);
        isCommentValid(commentText);
        Optional<Course> optionalCourse = courseRepository.findById(Long.parseLong(id));
        isCourseFound(optionalCourse);

        Course course = optionalCourse.get();
        Comment comment = new Comment();
        comment.setText(commentText);
        comment.setCourse(course);
        commentRepository.save(comment);
        course.adicionaComentario(comment);
        courseRepository.save(course);
        return AddCommentDTO.from(course);
    }

    public ReturnCommentDTO returnComment(String id) {
        isIdValid(id);
        Optional<Course> optionalCourse = courseRepository.findById(Long.parseLong(id));
        isCourseFound(optionalCourse);

        Course course = optionalCourse.get();
        List<CommentDTO> commentDTOList = returnCommentDTO(course.getCommentList());

        ReturnCommentDTO returnCommentDTO = new ReturnCommentDTO();
        returnCommentDTO.setCommentDTOList(commentDTOList);

        return returnCommentDTO;
    }

    public ReturnCourseTagDTO addTag(String id, String tagName) throws TagAlreadyRegisteredException {
        isIdValid(id);
        isTagValid(tagName);
        Optional<Course> optionalCourse = courseRepository.findById(Long.parseLong(id));
        isCourseFound(optionalCourse);

        Course course = optionalCourse.get();
        List<Tag> courseTagList = course.getTagList();

        for (Tag t : courseTagList) {
            if (t.getName().equalsIgnoreCase(tagName)) {
                throw new TagAlreadyRegisteredException("Tag não cadastrada", "Esta tag já foi cadastrada para essa disciplina");
            }
        }
        Tag tag = tagRepository.findByName(tagName).orElse(null);

        if (tag != null) {
            courseTagList.add(tag);
        } else {
            Tag newTag = new Tag();
            newTag.setName(tagName);
            tagRepository.save(newTag);
            courseTagList.add(newTag);
        }
        courseRepository.save(course);

        return ReturnCourseTagDTO.from(course);
    }

    public List<CourseDTO> orderCoursesByAverage() {
        List<Course> courseList = courseRepository.findAll();
        isCourseListEmpty(courseList);

        List<CourseDTO> courseDTOList = new ArrayList<>();
        for(Course d : courseList) {
            CourseDTO courseDTO = new CourseDTO(d);
            courseDTOList.add(courseDTO);
        }

        Collections.sort(courseDTOList, new Comparator<CourseDTO>() {
            public int compare(CourseDTO d1, CourseDTO d2) {
                return d1.comparaMedia(d2);
            }
        });
        return courseDTOList;
    }

    public List<CourseDTO> orderCoursesByLike() {
        List<Course> courseList = courseRepository.findAll();
        isCourseListEmpty(courseList);

        List<CourseDTO> courseDTOList = new ArrayList<>();
        for(Course d : courseList) {
            CourseDTO courseDTO = new CourseDTO(d);
            courseDTOList.add(courseDTO);
        }

        Collections.sort(courseDTOList, new Comparator<CourseDTO>() {
            public int compare(CourseDTO d1, CourseDTO d2) {
                return d1.comparaLike(d2);
            }
        });
        return courseDTOList;
    }

    public List<TagDTO> returnCourseTags(String id) {
        isIdValid(id);
        Optional<Course> optionalCourse = courseRepository.findById(Long.parseLong(id));
        isCourseFound(optionalCourse);

        Course course = optionalCourse.get();
        List<TagDTO> tagDTOList = new ArrayList<>();
        for(Tag tag : course.getTagList()) {
            TagDTO tagDTO = TagDTO.from(tag);
            tagDTOList.add(tagDTO);
        }
        return tagDTOList;
    }

    public List<ReturnCourseDTO> returnCoursesByTag(String tag) {
        isTagValid(tag);
        List<Course> courseList = courseRepository.findAll();
        isCourseListEmpty(courseList);
        List<ReturnCourseDTO> returnCourseListDTO = new ArrayList<>();

        for(Course course : courseList) {
            for(Tag t : course.getTagList()) {
                if(t.getName().equals(tag)) {
                    returnCourseListDTO.add(ReturnCourseDTO.from(course));
                }
            }
        }
        return returnCourseListDTO;
    }
}
