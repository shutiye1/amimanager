package safariami.manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safariami.manager.model.Job;
import safariami.manager.repo.JobRepository;
import safariami.manager.service.JobService;

@Service
public class JobServiceImpl implements JobService {
	
	@Autowired
	private JobRepository jobRepository;

	@Override
	public List<Job> findAll() {
		return jobRepository.findAll();
	}

	@Override
	public Optional<Job> findById(Long id) {
		return jobRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		jobRepository.deleteById(id);
	}

	@Override
	public Job save(Job job) {
		return jobRepository.save(job);
	}

	@Override
	public List<Job> findJobByName(String name) {
		return jobRepository.findJobByName(name);
	}

	@Override
	public List<Job> findJobByStatus(String status) {
		return jobRepository.findJobByStatus(status);
	}

	@Override
	public void deleteAll() {
		jobRepository.deleteAll();
	}

	@Override
	public long countAll() {
		return jobRepository.count();
	}
	
	@Override
	public List<Job> findJobByStatusAndName(String status, String name) {
		return jobRepository.findJobByStatusAndName(status, name);
	}

}
