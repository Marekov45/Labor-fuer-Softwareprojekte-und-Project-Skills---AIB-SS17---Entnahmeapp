package client_aib_labswp_2017_ss_entnahmeapp.View.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import client.aib_labswp_2017_ss_entnahmeapp.R;
import client_aib_labswp_2017_ss_entnahmeapp.View.controller.serverAPI.ListImpl;
import client_aib_labswp_2017_ss_entnahmeapp.View.controller.serverAPI.PrimerImpl;
import client_aib_labswp_2017_ss_entnahmeapp.View.model.NewLocation;
import client_aib_labswp_2017_ss_entnahmeapp.View.model.User;
import client_aib_labswp_2017_ss_entnahmeapp.View.model.model_List.PickList;
import client_aib_labswp_2017_ss_entnahmeapp.View.model.model_List.PrimerTube;

import java.util.List;

/**
 * Created by User on 14.06.2017.
 */
public class ListAdapterLastSanger extends ArrayAdapter<PrimerTube> {
    private int vg;
    private List<PrimerTube> primerTubes;
    private List<PickList> pickLists;
    Context context;
    ListImpl listImpl;
    User user;
    PrimerImpl primerImpl;


    public ListAdapterLastSanger(Context context, int vg, int id, List<PrimerTube> primerTubes, List<PickList> pickLists, User user, ListImpl listImpl, PrimerImpl primerImpl) {
        super(context, vg, id, primerTubes);

        this.context = context;
        this.primerTubes = primerTubes;
        this.pickLists = pickLists;
        this.vg = vg;
        this.user = user;
        this.listImpl = listImpl;
        this.primerImpl = primerImpl;
    }

    static class ViewHolder {
        public TextView txtPos;
        public TextView txtPrimer;
        public TextView txtStorageLocation;
        public TextView txtDestination;
        public Button manualScan;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(vg, parent, false);
            ListAdapterLastSanger.ViewHolder viewholder = new ListAdapterLastSanger.ViewHolder();
            viewholder.txtPos = (TextView) view.findViewById(R.id.txtPos);
            viewholder.txtPrimer = (TextView) view.findViewById(R.id.txtPrimer);
            viewholder.txtStorageLocation = (TextView) view.findViewById(R.id.txtStorageLocation);
            viewholder.txtDestination = (TextView) view.findViewById(R.id.txtDestination);

            view.setTag(viewholder);

        }

        final PrimerTube primerTube = primerTubes.get(position);
        final ListAdapterLastSanger.ViewHolder holder = (ListAdapterLastSanger.ViewHolder) view.getTag();

        holder.txtPos.setText(String.valueOf((position % 32) + 1));
        holder.txtPrimer.setText(primerTube.getName());
        holder.txtStorageLocation.setText(primerTube.getStorageLocation().toString());
        NewLocation location = new NewLocation(primerTube.getCurrentLocation());
        holder.txtDestination.setText(location.getNewLocation());
        return view;
    }

    public void changeRow(PrimerTube newTube, int positionForReplacement) {
        getItem(positionForReplacement - 1).setObjectID(newTube.getObjectID());
        getItem(positionForReplacement - 1).setTakeOutDate(newTube.getTakeOutDate());
        getItem(positionForReplacement - 1).setPutBackDate(newTube.getPutBackDate());
        getItem(positionForReplacement - 1).setPrimerTubeID(newTube.getPrimerTubeID());
        getItem(positionForReplacement - 1).setPrimerUID(newTube.getPrimerUID());
        getItem(positionForReplacement - 1).setName(newTube.getName());
        getItem(positionForReplacement - 1).setLotNr(newTube.getLotNr());
        getItem(positionForReplacement - 1).setManufacturer(newTube.getManufacturer());
        getItem(positionForReplacement - 1).setCurrentLocation(newTube.getCurrentLocation());
        getItem(positionForReplacement - 1).setStorageLocation(newTube.getStorageLocation());
        getItem(positionForReplacement - 1).setReturnToStorage(newTube.isReturnToStorage());
        notifyDataSetChanged();
    }

    public void changeCurrentLocation(PrimerTube tube, int positionForNewLocation, NewLocation newlocation){
        getItem(positionForNewLocation -1).setObjectID(tube.getObjectID());
        getItem(positionForNewLocation-1).setTakeOutDate(tube.getTakeOutDate());
        getItem(positionForNewLocation-1).setPutBackDate(tube.getPutBackDate());
        getItem(positionForNewLocation-1).setPrimerTubeID(tube.getPrimerTubeID());
        getItem(positionForNewLocation-1).setPrimerUID(tube.getPrimerUID());
        getItem(positionForNewLocation-1).setName(tube.getName());
        getItem(positionForNewLocation-1).setLotNr(tube.getLotNr());
        getItem(positionForNewLocation-1).setManufacturer(tube.getManufacturer());
        getItem(positionForNewLocation-1).setCurrentLocation(newlocation.getNewLocation());
        getItem(positionForNewLocation-1).setStorageLocation(tube.getStorageLocation());
        getItem(positionForNewLocation-1).setReturnToStorage(tube.isReturnToStorage());
        notifyDataSetChanged();
    }
}
