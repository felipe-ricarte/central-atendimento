package br.com.felipericarte.centralatendimento.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AtendimentoController.class)
public class AtendimentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AtendimentoService AtendimentoService;

    @MockBean
    private AtendimentoRepository AtendimentoRepository;

    private final String PATH = "/Atendimento";

    @Test
    public void criarAtendimento_ComDadosValidos_RetornandoCreated() throws Exception {
        when(AtendimentoService.criar(Atendimento_REQUEST_VALIDA_MOCK.toModel())).thenReturn(Atendimento_REQUEST_VALIDA_MOCK.toModel());
        mockMvc.perform(
                        post(PATH)
                                .content(objectMapper.writeValueAsString(Atendimento_REQUEST_VALIDA_MOCK))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
        ;

    }

    @Test
    public void criarAtendimento_ComDadosInvalidos_RetornandoBadRequest() throws Exception {
        mockMvc.perform(
                        post(PATH)
                                .content(objectMapper.writeValueAsString(Atendimento_REQUEST_INVALIDA_MOCK))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void finalizarAtendimento_ComStatusValido_ReturnsOk() throws Exception {
        when(AtendimentoService.criar(Atendimento_VALIDA_EM_ATENDIMENTO_MOCK.get())).thenReturn(Atendimento_VALIDA_EM_ATENDIMENTO_MOCK.get());

        when(AtendimentoRepository.findById(Atendimento_VALIDA_EM_ATENDIMENTO_MOCK.get().getId())).thenReturn(Atendimento_VALIDA_EM_ATENDIMENTO_MOCK);

        when(AtendimentoRepository.findTop1ByTipoAtendimentoAndStatusAtendimentoOrderByIdAsc(Atendimento_VALIDA_EM_ATENDIMENTO_MOCK.get().getTipoAtendimento(), StatusAtendimentoEnum.AGUARDANDO_ATENDIMENTO)).thenReturn(Optional.empty());

        when(AtendimentoService.buscarPorId(Atendimento_VALIDA_EM_ATENDIMENTO_MOCK.get().getId())).thenReturn(Atendimento_VALIDA_EM_ATENDIMENTO_MOCK.get());
        when(AtendimentoService.finalizar(Atendimento_VALIDA_EM_ATENDIMENTO_MOCK.get().getId())).thenReturn(ATENDIMENTO_VALIDA_FINALIZADA_MOCK);

        mockMvc.perform(
                        patch(PATH+"/"+Atendimento_VALIDA_EM_ATENDIMENTO_MOCK.get().getId()+"/finalizar")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }



}
