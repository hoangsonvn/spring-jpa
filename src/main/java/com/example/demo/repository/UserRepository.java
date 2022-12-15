package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
   // @EntityGraph(value = "UserEntity.roles")
    Optional<UserEntity> findById(Long id);


    // dùng jsonignore lên trường nào thì kq trả về k có trg đó
    /* với thằng manytomany, khi ta find nó sẽ kéo theo cả thằng con vào gây ra 2 câu lệnh
     *    - Vì vậy nên dùng @EntityGraph  và set EntityGraph vào câu query nếu ta muốn lấy ra thằng con mà không thêm lệnh
     *    - Việc ta JsonIgrone cả thằng con thì câu lệnh số 2 k xuất hiện nhưng thì ta cố tý chọt vào thằng con thì nó sẽ xuất hiện
     *    - Có jsonigrone thì kết quả trả về dạng json thiếu đi trường đó, nhưng  @EntityGraph vẫn chir dùng 1 câu quẻy lấy được thằng con mà k thêm câu phụ
     *
     * ==** Những điều trên vẫn áp dụng cho thằng slave --*
     *
     * == Dù set lazy load hay không thì không  ảnh hưởng đến đặc diểm @EntityGraph
     *  */

    /* Việc không dùng JsonIgnore thì khi ta find 1 value trong entity thì câu lệnh con cũng k xuất hiện, . vào sẽ sinh câu lệnh */
    /* NOTE ***
     * --- CHẠY RETURN Ở CONTROLLER  SẼ LẤY RA TOÀN BỘ DATA MODEL DÙ CÓ FETCHTYPE LAZY */

//    @EntityGraph(attributePaths = {"roles"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<UserEntity> findByRoles_Id(Long roleId);

    /*

    @EntityGraph
    type = EntityGraph.EntityGraphType.FETCH vs type = EntityGraph.EntityGraphType.LOAD
    LOAD lấy hết, FETCH lấy từng phần\

    "javax.persistence.fetchgraph vs javax.persistence.loadgraph
    Ex:..
      @Id
    String messageId;
    @Basic(fetch=EAGER)
    String subject;
    String body;
    @Basic(fetch=EAGER)
    String sender;
    @OneToMany(mappedBy="message", fetch=LAZY)
    Set<EmailAttachment> attachments;
   ..
    EntityGraph<EmailMessage> eg = em.createEntityGraph(EmailMessage.class);
eg.addAttributeNodes("body");
...
Properties props = new Properties();
props.put("javax.persistence.fetchgraph", eg);
EmailMessage message = em.find(EmailMessage.class, id, props);

vs javax.persistence.fetchgraph, chỉ trường body được chạy, các trường khác được bỏ qua
vs javax.persistence.loadgraph thì lấy hết

*/

}