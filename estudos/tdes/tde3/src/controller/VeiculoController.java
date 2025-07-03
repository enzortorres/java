package controller;

import model.*;
import model.excecao.*;
import java.util.ArrayList;

public class VeiculoController {
    private ArrayList<Veiculo> veiculos = new ArrayList<>();

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public ArrayList<Veiculo> listarVeiculos() {
        return veiculos;
    }

    public Veiculo buscarPorPlaca(String placa) throws VeiculoNaoEncontradoException {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        throw new VeiculoNaoEncontradoException("Veículo com placa " + placa + " não encontrado.");
    }

    public void atualizarVeiculo(String placa, String novaMarca, String novoModelo)
            throws VeiculoNaoEncontradoException {
        Veiculo v = buscarPorPlaca(placa);
        v.setMarca(novaMarca);
        v.setModelo(novoModelo);
    }

    public void removerVeiculo(String placa) throws VeiculoNaoEncontradoException {
        Veiculo v = buscarPorPlaca(placa);
        veiculos.remove(v);
    }
}
